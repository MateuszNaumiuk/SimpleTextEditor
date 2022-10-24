import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Window extends JFrame {
    private JPanel mainWindow;
    private JPanel fontColorPanel;
    private JTextArea textArea;
    private JComboBox fontCombo;
    private JComboBox fontSizeCombo;
    private JCheckBox wrappingCheckbox;
    private JButton snipButton;
    private JButton copyButton;
    private JButton pasteButton;
    private JPanel bcgPanel;
    private JPanel toolbar;
    private JCheckBox boldCheckBox;
    private JCheckBox cursiveCheckBox;
    private JButton fontColorButton;
    private JButton bgcButton;
    private JTextField numberOfChars;
    ToClipboard toClipboard = new ToClipboard();
    int fontSetup = 0;

    // File saving and loading
    public void SaveFile() throws IOException {
        FileWriter pw = new FileWriter("filename.txt");
        textArea.write(pw);
    }

    public void OpenFile() throws IOException {
        String content = Files.readString(Path.of("filename.txt"), StandardCharsets.UTF_8);
        this.textArea.setText(content);
    }

    private void CountChars() {
        numberOfChars.setText("Number of characters: " + textArea.getText().length());
    }

    public Window() {
        // Setup of characters number
        numberOfChars.setText("Number of characters: 0");
        textArea.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                CountChars();
            }

            public void keyReleased(KeyEvent e) {
                CountChars();
            }
        });

        // Setup of colorPanels
        fontColorPanel.setBackground(Color.WHITE);
        fontColorPanel.setMinimumSize(new Dimension(25, 5));
        fontColorPanel.setBackground(Color.black);
        fontColorButton.addActionListener(e -> {
            Color c = JColorChooser.showDialog(null, "Choose", Color.BLACK);
            textArea.setForeground(c);
            fontColorPanel.setBackground(c);
        });
        bcgPanel.setBackground(Color.WHITE);
        bcgPanel.setMinimumSize(new Dimension(25, 5));
        bgcButton.addActionListener(e -> {
            Color c = JColorChooser.showDialog(null, "Choose", Color.CYAN);
            textArea.setBackground(c);
            bcgPanel.setBackground(c);
        });

        // Starting settings of text
        fontCombo.setSelectedIndex(0);
        fontSizeCombo.setSelectedIndex(15);
        fontType.fontSetup(0, textArea, fontCombo, fontSizeCombo);

        // wrap checkbox
        wrappingCheckbox.addActionListener(e -> textArea.setLineWrap(wrappingCheckbox.isSelected()));

        // Font combo box
        fontCombo.addActionListener(e -> fontType.fontSetup(0, textArea, fontCombo, fontSizeCombo));
        fontSizeCombo.addActionListener(e -> fontType.fontSetup(0, textArea, fontCombo, fontSizeCombo));

        // if bold checkbox selected text is bold
        boldCheckBox.addActionListener(e -> {
            if (boldCheckBox.isSelected()) {
                fontSetup = 1; // bold
                fontType.fontSetup(fontSetup, textArea, fontCombo, fontSizeCombo);
            } else {
                fontSetup = 0; // plain
                fontType.fontSetup(fontSetup, textArea, fontCombo, fontSizeCombo);
            }
        });

        // if cursive checkbox is selected text is cursive
        cursiveCheckBox.addActionListener(e -> {
            if (cursiveCheckBox.isSelected()) {
                fontSetup = 2; // cursive
                fontType.fontSetup(fontSetup, textArea, fontCombo, fontSizeCombo);
            } else {
                fontSetup = 0; // plain
                fontType.fontSetup(fontSetup, textArea, fontCombo, fontSizeCombo);
            }
        });

        pasteButton.addActionListener(e -> textArea.paste());
        snipButton.addActionListener(e -> toClipboard.Snip(textArea));
        copyButton.addActionListener(e -> toClipboard.copyPaste(textArea));

        // setup of main window and navbar
        this.setTitle("Notepad");
        this.setContentPane(mainWindow);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(1000, 600));
        this.setVisible(true);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem open = new JMenuItem("open");
        JMenuItem save = new JMenuItem("save");
        JMenuItem close = new JMenuItem("close");
        menu.add(open);
        menu.add(save);
        menu.add(close);
        open.addActionListener(e -> {
            try {
                OpenFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        save.addActionListener(e -> {
            try {
                SaveFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        close.addActionListener(e -> this.dispose());
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
    }
}