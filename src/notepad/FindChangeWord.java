/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;

public class FindChangeWord {

    private String foundWord = "";

    //Find given text and highlight them.
    public String findGivenText(String givenText, JTextPane textPane) throws BadLocationException {

        Highlighter highlight = textPane.getHighlighter(); //to highlight the found word
        highlight.removeAllHighlights(); //deleting the previous highlight when searching for another word
        Document doc = textPane.getDocument(); //getting writings in the notepad's screen
        Highlighter.HighlightPainter myHighlighter = new MyHighlight(Color.YELLOW);
        String text = doc.getText(0, doc.getLength());
        int pos = 0;

        while ((pos = text.toUpperCase().indexOf(givenText.toUpperCase(), pos)) >= 0) {
            // indexOf returns a value greater than 0 if the search word exists in textPane
            highlight.addHighlight(pos, pos + givenText.length(), myHighlighter);//highlight operation starting from the first index of the given word up to its length
            foundWord = text.substring(pos, pos + givenText.length());
            pos += givenText.length();

        }

        return foundWord;

    }

    //Change given text.
    public void changeGivenText(String findText, String changeText, JTextPane textPane) throws BadLocationException {
        Document doc = textPane.getDocument();
        String text = doc.getText(0, doc.getLength());

        text = text.replaceAll(findText, changeText); //replaces the given word with the desired word in textpane.
        textPane.setText(text);
    }

    class MyHighlight extends DefaultHighlighter.DefaultHighlightPainter {

        public MyHighlight(Color c) {
            super(c);
        }

    }

}
