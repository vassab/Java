package org.example;

public class PalindromeChecker {

    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }

        String cleanWord = word.replaceAll("\\s+", "").toLowerCase();
        int length = cleanWord.length();
        for (int i = 0; i < length / 2; i++) {
            if (cleanWord.charAt(i) != cleanWord.charAt(length - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
