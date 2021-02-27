/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

import java.util.ArrayList;
import javax.swing.JTextPane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class SingleTranspositionTest {
    
 
 
  

    @Test
    public void it_should_return_ascii_values_of_each_word() throws Exception {

        
        //Given
        String str = "example";
        SingleTransposition instance = new SingleTransposition();
        
        //when
        int ascii =instance.getASCIISum(str);
        
        //Then
         Assert.assertEquals(748,ascii);
    }
    
        

    @Test
    public void it_should_return_edited_text_pane_when_text_has_single_transpositions() throws Exception {
     
        //Given
        String currentWord = "Lorme";
        String currentWord2 = "ispum";
        ArrayList<String> arr = new ArrayList<>();
        
        arr.add("Lorem");
        arr.add("ipsum");
        arr.add("dolor");
        JTextPane textPane = new JTextPane();
        textPane.setText("Lorme ispum dolor");
        SingleTransposition instance = new SingleTransposition();
        
        //When
        instance.getTrueWord(currentWord, arr, textPane);
        instance.getTrueWord(currentWord2, arr, textPane);
        
        
        //Then
        Assert.assertEquals("Lorem ipsum dolor",textPane.getText());

        
    }

  
    
}
