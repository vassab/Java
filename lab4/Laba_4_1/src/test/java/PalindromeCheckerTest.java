import static org.junit.jupiter.api.Assertions.*;

import org.example.PalindromeChecker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class PalindromeCheckerTest {

    private PalindromeChecker palindrom;

    @BeforeEach
    void setUp() {
        palindrom = new PalindromeChecker();
    }

    @Test
    void testPalindromeWord() {
        assertTrue(palindrom.isPalindrome("radar"));
        assertTrue(palindrom.isPalindrome("Racecar"));
    }

    @Test
    void testNonPalindromeWord() {
        assertFalse(palindrom.isPalindrome("Java"));
    }

    @Test
    void testEmptyString() {
        assertTrue(palindrom.isPalindrome(""));
    }

    @Test
    void testNull() {
        assertFalse(palindrom.isPalindrome(null));
    }

    @Test
    void testPalindromeSentence() {
        assertTrue(palindrom.isPalindrome("A man a plan a canal Panama"));
    }
}
