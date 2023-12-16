package org.example.textfileapp;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import org.example.textfileapp.FileHandler;

public class TextFileAppUI {
    private JFrame frame;
    private JTextArea textArea;
    private FileHandler fileHandler;
    private TextEncoderDecoder encoderDecoder;

    public TextFileAppUI(FileHandler fileHandler, TextEncoderDecoder encoderDecoder) {
        this.fileHandler = fileHandler;
        this.encoderDecoder = encoderDecoder;
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Text File Encoder/Decoder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        textArea = new JTextArea();
        frame.add(new JScrollPane(textArea));

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Файл");
        JMenuItem openItem = new JMenuItem("Открыть");
        JMenuItem saveItem = new JMenuItem("Сохранить");
        JMenuItem encodeItem = new JMenuItem("Кодировать");
        JMenuItem decodeItem = new JMenuItem("Декодировать");

        openItem.addActionListener(e -> fileHandler.openFile(frame, textArea));
        saveItem.addActionListener(e -> fileHandler.saveFile(frame, textArea));


        encodeItem.addActionListener(e -> {
            textArea.setText(encoderDecoder.encodeText(textArea.getText(), "secret"));
            JOptionPane.showMessageDialog(frame, "Текст был успешно закодирован.");
        });

        decodeItem.addActionListener(e -> {
            textArea.setText(encoderDecoder.decodeText(textArea.getText(), "secret"));
            JOptionPane.showMessageDialog(frame, "Текст был успешно декодирован.");
        });

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(encodeItem);
        fileMenu.add(decodeItem);
        menuBar.add(fileMenu);

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }
}
