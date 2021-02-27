/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;

/**
 *
 * @author sila.eryilmaz
 */
public class CreateFileStrategy implements MenuStrategy {

    @Override
    public void executeProcess(JFileChooser fc, JTextPane jTextPane, File file) {
        if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            FileWriter fw = null;
            try {
                fw = new FileWriter(fc.getSelectedFile().getAbsolutePath() + ".txt");
                jTextPane.write(fw);
                fw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
