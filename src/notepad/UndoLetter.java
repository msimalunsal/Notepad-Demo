/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

import javax.swing.JOptionPane;
import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.UndoManager;

public class UndoLetter implements UndoableCommand {

    public UndoManager manager;
    UndoableEditEvent editEvent;

    public UndoLetter(UndoManager manager, UndoableEditEvent editEvent) {
        this.manager = manager;
        this.editEvent = editEvent;
    }

    @Override
    public void undo(UndoableEditEvent undoableEditEvent) {
        manager.addEdit(undoableEditEvent.getEdit());
    }

    @Override
    public void execute() {
        if (manager.canUndo()) {
            manager.undo(); //undo letter
        } else {
            //When no more letter in textfile
            infoBox("No more letters!", "Warning!");
        }
    }

    public static void infoBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

}
