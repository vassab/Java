import org.example.Decoder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecoderTest {

    // Тест для перевірки декодування голосних
    @Test
    public void testDecodeVowelEncoding() {
        assertEquals("testing", Decoder.decodeVowelEncoding("t2st3ng"), "Vowel decoding failed");
    }

    // Тест для перевірки декодування приголосних
    @Test
    public void testDecodeConsonantEncoding() {
        assertEquals("testing", Decoder.decodeConsonantEncoding("vetviph"), "Consonant decoding failed");
    }

    // Тест для перевірки визначення та декодування методу кодування
    @Test
    public void testDecodeWord() {
        assertEquals("testing", Decoder.decodeWord("t2st3ng"), "Decode word with vowel encoding failed");
        assertEquals("testing", Decoder.decodeWord("vetviph"), "Decode word with consonant encoding failed");
    }
}
