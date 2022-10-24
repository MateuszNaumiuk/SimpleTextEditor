import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class ToClipboard implements ClipboardOwner {
    String startText;
    String endText;


    public void setClipboard(String aString){
        StringSelection stringSelection = new StringSelection(aString);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, this);
    }

    @Override
    public void lostOwnership(Clipboard aclipboard, Transferable acontents) {}
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();;

    public void copyPaste(JTextArea textArea){
        String selectedTxt = textArea.getSelectedText();
        StringSelection stringSelection = new StringSelection(selectedTxt);
        int start = textArea.getSelectionStart();
        int end = textArea.getSelectionEnd();
        startText = textArea.getText().substring(0,start);
        endText = textArea.getText().substring(end);
        clipboard.setContents(stringSelection, null);
    }

    public void Snip(JTextArea textArea){
        copyPaste(textArea);
        textArea.setText(startText + endText);
    }
}
