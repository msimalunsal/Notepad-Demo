/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

import java.io.File;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;

/**
 *
 * @author sila.eryilmaz
 */
public class OpenFileStrategy implements MenuStrategy {

    @Override
    public void executeProcess(JFileChooser fc, JTextPane jTextPane, File file) {
        String text = "";
        JFileChooser j = new JFileChooser();

        int r = j.showOpenDialog(null);

        if (r == JFileChooser.APPROVE_OPTION) {

            File fi = new File(j.getSelectedFile().getAbsolutePath());
            file = fi;
            try {

                FileReader fr = new FileReader(fi);
                int i = fr.read();

                while (i != -1) {
                    text += (char) i;
                    i = fr.read();
                }
                System.out.print(text);

            } catch (Exception evt) {

            }
        }
        jTextPane.setText(text);
        Notepad.file = file;
    }

}
