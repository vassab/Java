package org.example;

import java.util.regex.*;

public class Decoder {

    public static String decodeVowelEncoding(String word) {
        return word.replace("1", "a")
                .replace("2", "e")
                .replace("3", "i")
                .replace("4", "o")
                .replace("5", "u");
    }

    public static String decodeConsonantEncoding(String word) {
        StringBuilder decodedWord = new StringBuilder();
        String consonants = "bcdfghjklmnpqrstvwxz";
        for (char ch : word.toCharArray()) {
            if (consonants.indexOf(ch) != -1) {
                int pos = consonants.indexOf(ch);
                char decodedChar = (pos == 0) ? 'z' : consonants.charAt(pos - 1);
                decodedWord.append(decodedChar);
            } else {
                decodedWord.append(ch);
            }
        }
        return decodedWord.toString();
    }

    public static String decodeWord(String word) {
        if (word.matches(".*[1-5].*")) {
            return decodeVowelEncoding(word);
        } else if (word.matches(".*[bcdfghjklmnpqrstvwxyz].*")) {
            return decodeConsonantEncoding(word);
        }
        return word; // Якщо слово не закодоване, повертаємо як є
    }

}
