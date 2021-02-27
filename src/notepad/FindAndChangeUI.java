/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

public class FindAndChangeUI implements Operation {

    public JTextField tf1;
    public JTextField tf2;
    public JButton button1;
    public JFrame f;
    public JTextPane textPane;

    @Override
    public void makeOperation(JTextPane jTextPane) {
        textPane = jTextPane;
        f = new JFrame("Find and Change");

        JLabel label1 = new JLabel("Find");
        label1.setBounds(30, 50, 50, 30);
        tf1 = new JTextField();
        tf1.setBounds(110, 50, 100, 30);
        button1 = new JButton("OK");
        button1.setBounds(220, 50, 30, 30);

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FindChangeWord findWord = new FindChangeWord();
                String givenText = tf1.getText();
                try {
                    findWord.findGivenText(givenText, textPane);//when button is clicked, sends the value of the entered text to the find method
                } catch (BadLocationException ex) {
                    Logger.getLogger(FindAndChangeUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        JLabel label2 = new JLabel("Change");
        label2.setBounds(30, 100, 50, 30);
        tf2 = new JTextField();
        tf2.setBounds(110, 100, 100, 30);
        JButton button2 = new JButton("OK");
        button2.setBounds(220, 100, 30, 30);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FindChangeWord changeWord = new FindChangeWord();
                String findText = tf1.getText();
                String givenText = tf2.getText();
                try {
                    changeWord.changeGivenText(findText, givenText, textPane); //when button is clicked, sends the value of the entered text to the change method
                } catch (BadLocationException ex) {
                    Logger.getLogger(FindAndChangeUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        f.add(label1);
        f.add(tf1);
        f.add(button1);
        f.add(label2);
        f.add(tf2);
        f.add(button2);

        f.setSize(300, 300);
        f.setLayout(null);
        f.setVisible(true);
    }

}
