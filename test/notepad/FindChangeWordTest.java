package notepad;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.JTextPane;
import org.junit.Assert;
import org.junit.Test;




public class FindChangeWordTest {
    
  

    @Test
    public void it_should_return_searched_word_when_search_a_word() throws Exception {
        
        //Given
        String givenText = "bir";
        JTextPane textPane= new JTextPane();
        textPane.setText("Bu bir test denemesidir.");
        
        //When
        FindChangeWord instance = new FindChangeWord();
        String foundWord =instance.findGivenText(givenText, textPane);
        
        
        //Then
        Assert.assertEquals("bir", foundWord);
        
    }

   
    @Test
    public void it_should_return_modified_word_when_change_a_word() throws Exception {

 
       //Given
        String findText = "gunesli";
        String changeText = "yagmurlu";
        JTextPane textPane = new JTextPane();
        textPane.setText("Bugun gunesli bir gundu.");
        
        //When
        FindChangeWord instance = new FindChangeWord();
        instance.changeGivenText(findText, changeText, textPane);
        
        //Then
        Assert.assertEquals("Bugun yagmurlu bir gundu.",textPane.getText());
    }
    
    @Test
    public void it_should_return_modified_words_when_change_multiple_words() throws Exception {

 
       //Given
        String findText = "gunesli bir gundu.";
        String changeText = "yagmurlu bir geceydi.";
        JTextPane textPane = new JTextPane();
        textPane.setText("Bugun gunesli bir gundu.");
        
        //When
        FindChangeWord instance = new FindChangeWord();
        instance.changeGivenText(findText, changeText, textPane);
        
        //Then
        Assert.assertEquals("Bugun yagmurlu bir geceydi.",textPane.getText());
    }
    
}
