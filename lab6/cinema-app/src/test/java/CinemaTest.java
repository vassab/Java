import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CinemaTest {
    private Cinema cinema;

    @Before
    public void setUp() {
        cinema = new Cinema(5, 10, 20); // Ініціалізуємо кінотеатр з 5 залами, 10 рядами та 20 місцями.
    }

    @Test
    public void bookAndCancelSeats() {
        int hallNumber = 1;
        int row = 3;
        int[] seats = {4, 5, 6};

        // Бронювання місць
        try {
            cinema.bookSeats(hallNumber, row, seats);
        } catch (BookingException e) {
            fail("Не повинно бути помилки бронювання: " + e.getMessage());
        }

        // Скасування бронювання
        try {
            cinema.cancelBooking(hallNumber, row, seats);
        } catch (BookingException e) {
            fail("Не повинно бути помилки скасування бронювання: " + e.getMessage());
        }
    }

    @Test
    public void checkAvailability() {
        int hallNumber = 1;
        int numSeats = 5;

        // Перевірка наявності послідовних місць у ряду
        boolean available = cinema.checkAvailability(hallNumber, numSeats);
        assertTrue(available);

        numSeats = 15; // Намагаємося забронювати більше місць, ніж доступно в ряду
        available = cinema.checkAvailability(hallNumber, numSeats);
        assertFalse(available);
    }

    @Test
    public void findBestAvailable() {
        int hallNumber = 1;
        int numSeats = 5;

        // Пошук найкращих доступних послідовних місць
        int[] bestAvailable = cinema.findBestAvailable(hallNumber, numSeats);
        assertNotNull(bestAvailable);
        assertEquals(5, bestAvailable.length);

        // Намагаємося забронювати більше місць, ніж доступно в ряду
        numSeats = 25;
        bestAvailable = cinema.findBestAvailable(hallNumber, numSeats);
        assertNull(bestAvailable);
    }
}
