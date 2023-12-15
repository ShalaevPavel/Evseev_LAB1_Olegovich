package org.example.textfileapp;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

public class TextFileApp {
    private JFrame frame;
    private JTextArea textArea;
    private File currentFile;

    public TextFileApp() {
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

        openItem.addActionListener(e -> openFile());
        saveItem.addActionListener(e -> saveFile());
        encodeItem.addActionListener(e -> encodeText());
        decodeItem.addActionListener(e -> decodeText());

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(encodeItem);
        fileMenu.add(decodeItem);
        menuBar.add(fileMenu);

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Текстовые файлы", "txt"));

        int returnValue = fileChooser.showOpenDialog(frame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            readFile(currentFile);
        }
    }

    private void readFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            textArea.setText("");
            String line;
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Ошибка при чтении файла: " + e.getMessage());
        }
    }

    private void saveFile() {
        if (currentFile == null) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setFileFilter(new FileNameExtensionFilter("Текстовые файлы", "txt"));

            int returnValue = fileChooser.showSaveDialog(frame);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                currentFile = fileChooser.getSelectedFile();
            } else {
                return;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
            writer.write(textArea.getText());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Ошибка при записи файла: " + e.getMessage());
        }
    }

    private void encodeText() {
        String text = textArea.getText();
        String encodedText = applyXOR(text, "secret"); // Здесь "secret" - это ключ для XOR
        textArea.setText(encodedText);
    }

    private void decodeText() {
        String encodedText = textArea.getText();
        String decodedText = applyXOR(encodedText, "secret"); // Использование того же ключа для декодирования
        textArea.setText(decodedText);
    }

    private String applyXOR(String text, String key) {
        char[] keys = key.toCharArray();
        char[] textChars = text.toCharArray();

        for (int i = 0; i < textChars.length; i++) {
            textChars[i] = (char) (textChars[i] ^ keys[i % keys.length]);
        }

        return new String(textChars);
    }


}
