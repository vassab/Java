package org.example;

public class Main {
    public static void main(String[] args) {
        String[] encodedWords = {"t2st3ng", "vetviph"};
        for (String word : encodedWords) {
            System.out.println("Original: " + word + " -> Decoded: " +Decoder.decodeWord(word));
        }
    }
}