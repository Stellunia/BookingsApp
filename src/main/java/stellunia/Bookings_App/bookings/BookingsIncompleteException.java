package stellunia.Bookings_App.bookings;

public class BookingsIncompleteException extends BookingsException {

    public BookingsIncompleteException(String message) {
        super(message);
    }

    public BookingsIncompleteException(String message, Throwable cause) {
        super(message, cause);
    }
}
