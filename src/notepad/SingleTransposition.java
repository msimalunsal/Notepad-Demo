/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import java.util.Iterator;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SingleTransposition implements Operation {

    private ArrayList<String> wordsArray = new ArrayList<>();
    private ArrayList<String> textPaneArray = new ArrayList<>();
    private ArrayList<String> sameASCIIarr;
    private String s = "";
    private FindChangeWord findCh = new FindChangeWord();
    private Iterator iterator;
    private Iterator iteratorWordArray;
    private Iterator iteratorTextPaneArray;

    private CharacterIterator characterIterator;

    private String getS() {
        return s;
    }

    public void readFile() throws FileNotFoundException, IOException {

        //This method reads word.txt and makes wordsArray from this txt. 
        try {
            FileInputStream fstream_words = new FileInputStream("words.txt"); //read words.txt to find typo
            DataInputStream data_input = new DataInputStream(fstream_words);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(data_input));
            String str_line;

            while ((str_line = buffer.readLine()) != null) {
                str_line = str_line.trim();
                if ((str_line.length() != 0)) {
                    wordsArray.add(str_line);
                }
            }

        } catch (Exception e) {
            System.out.println("Couldn't read words.txt");
        }
    }

    public void checkASCIIValues(JTextPane textPane) throws BadLocationException {

        /*When searching for single transpositions, we first find the ones whose ascii values are equal to each other, 
         and we are eliminating to make fewer comparisons rather than the whole list.*/
        iterator = wordsArray.iterator();
        iteratorTextPaneArray = textPaneArray.iterator();

        while (iteratorTextPaneArray.hasNext()) {

            sameASCIIarr = new ArrayList<>();
            String textArrayElement = iteratorTextPaneArray.next().toString();
            iterator = wordsArray.listIterator(0);
            while (iterator.hasNext()) {
                String wordsArrayElement = iterator.next().toString();
                if (getASCIISum(textArrayElement) == getASCIISum(wordsArrayElement)) {
                    //compare ASCII values with words.txt and textPane (notepad screen) 
                    s = textArrayElement;
                    sameASCIIarr.add(wordsArrayElement);
                }
                //wordsArrayElement = iterator.next().toString();//adds words that have same ASCII values. 
            }
            getTrueWord(s, sameASCIIarr, textPane);
        }
    }

    public void getTrueWord(String currentWord, ArrayList arr, JTextPane textPane) throws BadLocationException {

        String tempWord = currentWord; //we keep currentWord 
        char[] currentWordChar = currentWord.toCharArray();
        characterIterator = new StringCharacterIterator(currentWordChar.toString());
        iteratorWordArray = arr.iterator();

        String listIteratorElement;
        while (iteratorWordArray.hasNext()) {
            listIteratorElement = iteratorWordArray.next().toString();

            for (char c = characterIterator.first(); c != CharacterIterator.DONE; c = characterIterator.next()) { //loops up to sameASCII arr size

                for (int j = 0; j < currentWord.length() - characterIterator.getIndex() - 1; j++) {
                    if (listIteratorElement.equals(currentWord)) //If the word in textpane and word in words.txt is the same 
                    {
                        findCh.changeGivenText(tempWord, listIteratorElement, textPane);
                    }
                    currentWord = tempWord;
                    currentWordChar = currentWord.toCharArray();

                    //checking transposition cases in order in the word
                    char temp = currentWordChar[j];
                    currentWordChar[j] = currentWordChar[j + 1];
                    currentWordChar[j + 1] = temp;
                    currentWord = String.valueOf(currentWordChar);

                }
            }
        }

    }

    public int getASCIISum(String string) {
        //finds ASCII values of words        
        int sum = 0;
        for (int x = 0; x < string.length(); x++) {
            sum += string.charAt(x);
        }
        return sum;
    }

    public void createTextPaneArray(JTextPane textPane) {
        for (String a : textPane.getText().split("\\s++")) {

            textPaneArray.add(a);
        }

    }

    @Override
    public void makeOperation(JTextPane textPane) {

        try {
            readFile();

            createTextPaneArray(textPane);

            try {
                checkASCIIValues(textPane);
            } catch (BadLocationException ex) {
                Logger.getLogger(SingleTransposition.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(SingleTransposition.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
