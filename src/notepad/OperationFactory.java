/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

/**
 *
 * @author sila.eryilmaz
 */
public class OperationFactory extends AbstractFactory {

    public Operation makeOperationObject(String objectType) {
        if (objectType.equalsIgnoreCase("Single Transposition")) {
            return (Operation) new SingleTransposition();
        } else if (objectType.equalsIgnoreCase("Find and Change Word")) {
            return (Operation) new FindAndChangeUI();

        }
        return null;

    }

}
