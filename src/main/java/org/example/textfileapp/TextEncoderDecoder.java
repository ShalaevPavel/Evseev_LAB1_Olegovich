package org.example.textfileapp;

public class TextEncoderDecoder {
    public String encodeText(String text, String key) {
        return applyXOR(text, key);
    }

    public String decodeText(String text, String key) {
        return applyXOR(text, key);
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

