package stellunia.Bookings_App.bookings;

public class BookingsException extends RuntimeException {

    public BookingsException(String message) {
        super(message);
    }

    public BookingsException(String message, Throwable cause) {
        super(message, cause);
    }
}
