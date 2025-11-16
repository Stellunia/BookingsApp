package stellunia.Bookings_App.bookings;

public class BookingIncompleteException extends BookingsException {

    public BookingIncompleteException(String message) {
        super(message);
    }

    public BookingIncompleteException(String message, Throwable cause) {
        super(message, cause);
    }
}
