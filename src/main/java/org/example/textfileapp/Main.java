package org.example.textfileapp;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler();
        TextEncoderDecoder encoderDecoder = new TextEncoderDecoder();
        new TextFileAppUI(fileHandler, encoderDecoder);
    }
}
