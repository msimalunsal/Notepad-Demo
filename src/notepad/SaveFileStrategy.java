/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;

/**
 *
 * @author sila.eryilmaz
 */
public class SaveFileStrategy implements MenuStrategy {

    private File file = null;
    private Boolean isFileCreatedCheck = null;

    @Override
    public void executeProcess(JFileChooser fc, JTextPane jTextPane, File file) {
        FileWriter fw = null;

        try {
            fw = new FileWriter(file.getAbsolutePath());
        } catch (IOException ex) {
            Logger.getLogger(SaveFileStrategy.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            fw.write(jTextPane.getText()); //writes everything in the file to textPane
        } catch (IOException ex) {
            Logger.getLogger(SaveFileStrategy.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(SaveFileStrategy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
