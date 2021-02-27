/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

import javax.swing.event.UndoableEditEvent;

/**
 *
 * @author sila.eryilmaz
 */
public interface UndoableCommand extends Command {

    public void undo(UndoableEditEvent undoableEditEvent);

}
