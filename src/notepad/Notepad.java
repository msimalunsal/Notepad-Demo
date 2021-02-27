package notepad;

import java.awt.Dimension;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileReader;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.undo.UndoManager;

public class Notepad {

    private JFrame jframe = new JFrame();
    private JDialog jdialog = new JDialog();

    private JFileChooser fc = new JFileChooser();
    private FileReader fr = null;
    public static File file = null;

    private MenuContext menuContext = new MenuContext();
    private OperationFactory operationFactory = new OperationFactory();

    //for undo and event 
    UndoManager undoManager = new UndoManager();
    UndoableEditEvent editEvent; //created to detect and save changes made to the document
    UndoableCommand undoableCommand = new UndoLetter(undoManager, editEvent);

    //for style context and get textpane
    StyleContext context = new StyleContext();
    StyledDocument document = new DefaultStyledDocument(context);
    JTextPane textPane = new JTextPane(document);
    Document doc = textPane.getDocument();

    public Notepad() {

        //otepad page creation operations at startup
        JScrollPane scrollPane = new JScrollPane(textPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height * 2 / 3;
        int width = screenSize.width * 2 / 3;
        jframe.setPreferredSize(new Dimension(width, height));
        jframe.add(scrollPane);
        JMenuBar menuBar = new JMenuBar();
        jframe.setJMenuBar(menuBar);
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu undo = new JMenu("Undo");
        JMenu fixText = new JMenu("Fix Text");

        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(undo);
        menuBar.add(fixText);

        undo.add(Undo);

        file.add(Open);
        file.add(Create);
        file.add(Save);
        file.addSeparator();
        file.add(Exit);
        edit.add(FindAndChange);
        fixText.add(SingleTransposition);

        jframe.setTitle("Yeni Metin Belgesi");
        jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);

        doc.addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent evt) {
                undoableCommand.undo(evt);
                // belgedeki her işlem kaydedilir.
            }
        });
    }

    //Undo Button Action
    Action Undo = new AbstractAction("Geri Al") {
        @Override
        public void actionPerformed(ActionEvent e) {
            undoableCommand.execute();
        }
    };

    //SingleTransposition Button Action
    Action SingleTransposition = new AbstractAction("Single Transposition") {
        @Override
        public void actionPerformed(ActionEvent e) {
            Operation singleTransposition = operationFactory.makeOperationObject("Single Transposition");
            singleTransposition.makeOperation(textPane);
        }
    };

    //FindAndChange Action. Instantiate new frame.
    Action FindAndChange = new AbstractAction("Find and Change Word") {
        @Override
        public void actionPerformed(ActionEvent e) {
            Operation findAndChangeWord = operationFactory.makeOperationObject("Find and Change Word");
            findAndChangeWord.makeOperation(textPane);
        }
    };

    //Open File Action 
    Action Open = new AbstractAction("Open") {
        @Override
        public void actionPerformed(ActionEvent e) {
            //menu context ile gerekli strateji nesnesini yaratma.
                menuContext.setMenuStrategy(new OpenFileStrategy());
                menuContext.selectMenuItem(fc, textPane, null);
        }
    };

    //Save File Action
    Action Save = new AbstractAction("Save") {
        @Override
        public void actionPerformed(ActionEvent e) {
            //menu context ile gerekli strateji nesnesini yaratma.
            menuContext.setMenuStrategy(new SaveFileStrategy());
            menuContext.selectMenuItem(fc, textPane, file);
        }
    };

    //Create File Action
    Action Create = new AbstractAction("Create") {
        @Override
        public void actionPerformed(ActionEvent e) {
            //menu context ile gerekli strateji nesnesini yaratma.
            menuContext.setMenuStrategy(new CreateFileStrategy());
            menuContext.selectMenuItem(fc, textPane, null);
        }
    };

    //Exit File Action
    Action Exit = new AbstractAction("Exit") {
        @Override
        public void actionPerformed(ActionEvent e) {
            //menu context ile gerekli strateji nesnesini yaratma.
            menuContext.setMenuStrategy(new ExitFileStrategy());
            menuContext.selectMenuItem(fc, textPane, null);
        }
    };

    public static void main(String[] args) {

        new Notepad();

    }

    /**
     * @param file the file to set
     */
    public void setFile(File file) {
        this.file = file;
    }

}
