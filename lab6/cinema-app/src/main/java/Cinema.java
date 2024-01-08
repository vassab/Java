public class Cinema {
    private int[][][] seats;

    public Cinema(int halls, int rows, int seatsPerRow) {
        seats = new int[halls][rows][seatsPerRow];
    }

    public void bookSeats(int hallNumber, int row, int[] seatsToBook) {
        for (int seat : seatsToBook) {
            if (seats[hallNumber][row][seat] == 0) {
                seats[hallNumber][row][seat] = 1;
                System.out.println("Місце " + row + "-" + seat + " в залі " + hallNumber + " успішно заброньоване.");
            } else {
                System.out.println("Місце " + row + "-" + seat + " в залі " + hallNumber + " вже заброньоване.");
            }
        }
    }

    public void cancelBooking(int hallNumber, int row, int[] seatsToCancel) {
        for (int seat : seatsToCancel) {
            if (seats[hallNumber][row][seat] == 1) {
                seats[hallNumber][row][seat] = 0;
                System.out.println("Бронювання на місці " + row + "-" + seat + " в залі " + hallNumber + " скасоване.");
            } else {
                System.out.println("Місце " + row + "-" + seat + " в залі " + hallNumber + " не було заброньоване.");
            }
        }
    }

    public boolean checkAvailability(int hallNumber, int numSeats) {
        int consecutiveAvailable = 0;
        for (int row = 0; row < seats[hallNumber].length; row++) {
            for (int seat = 0; seat < seats[hallNumber][row].length; seat++) {
                if (seats[hallNumber][row][seat] == 0) {
                    consecutiveAvailable++;
                    if (consecutiveAvailable == numSeats) {
                        return true;
                    }
                } else {
                    consecutiveAvailable = 0;
                }
            }
        }
        return false;
    }

    public void printSeatingArrangement(int hallNumber) {
        for (int row = 0; row < seats[hallNumber].length; row++) {
            for (int seat = 0; seat < seats[hallNumber][row].length; seat++) {
                if (seats[hallNumber][row][seat] == 0) {
                    System.out.print("O "); // Вільне місце
                } else {
                    System.out.print("X "); // Заброньоване місце
                }
            }
            System.out.println();
        }
    }

    public int[][] findBestAvailable(int hallNumber, int numSeats) {
        int consecutiveAvailable = 0;
        int startSeat = 0;
        int bestRow = -1;
        int bestStartSeat = -1;

        for (int row = 0; row < seats[hallNumber].length; row++) {
            for (int seat = 0; seat < seats[hallNumber][row].length; seat++) {
                if (seats[hallNumber][row][seat] == 0) {
                    if (consecutiveAvailable == 0) {
                        startSeat = seat;
                    }
                    consecutiveAvailable++;
                    if (consecutiveAvailable == numSeats) {
                        return new int[]{row, startSeat};
                    }
                } else {
                    consecutiveAvailable = 0;
                }
            }
        }

        return null;
    }

    public void autoBook(int hallNumber, int numSeats) {
        int[] bestAvailable = findBestAvailable(hallNumber, numSeats);
        if (bestAvailable != null) {
            int row = bestAvailable[0];
            int startSeat = bestAvailable[1];
            int[] seatsToBook = new int[numSeats];
            for (int i = 0; i < numSeats; i++) {
                seatsToBook[i] = startSeat + i;
            }
            bookSeats(hallNumber, row, seatsToBook);
        } else {
            System.out.println("Неможливо забронювати " + numSeats + " послідовних місць.");
        }
    }

    public static void main(String[] args) {
        Cinema cinema = new Cinema(5, 10, 20);
        cinema.bookSeats(0, 3, new int[]{5, 6, 7});
        cinema.bookSeats(1, 5, new int[]{2, 3, 4, 5, 6});
        cinema.cancelBooking(0, 3, new int[]{6, 7});
        cinema.printSeatingArrangement(0);
        System.out.println("Наявність 3 послідовних місць: " + cinema.checkAvailability(0, 3));
        cinema.autoBook(2, 4);
        cinema.printSeatingArrangement(2);
    }
}
