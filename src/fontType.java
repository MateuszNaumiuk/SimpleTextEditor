import javax.swing.*;
import java.awt.*;

public class fontType {
    public static void fontSetup(int fontType, JTextArea textArea, JComboBox fontCombo, JComboBox fontSizeCombo){
        //ustawienie startowych zmiennych
        final int[] fontSize = new int[1];
        final String[] fontSet = new String[1];

        // ustawienie fontu i rozmiaru po wybraniu z combobox - font
        fontSet[0] = (String)fontCombo.getSelectedItem();
        fontSize[0] = fontSizeCombo.getSelectedIndex();
        Font font = new Font(fontSet[0], fontType, fontSize[0]);
        textArea.setFont(font);
    }
}
