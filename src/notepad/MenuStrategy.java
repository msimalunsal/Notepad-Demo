/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;

/**
 *
 * @author sila.eryilmaz
 */
public interface MenuStrategy {
    
    public void executeProcess(JFileChooser fc, JTextPane jTextPane, File file);
    
}
    

