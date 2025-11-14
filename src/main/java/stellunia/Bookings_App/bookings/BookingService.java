package stellunia.Bookings_App.bookings;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class BookingService {

    @Autowired
    private final BookingRepository bookingRepository;

    @Transactional
    public Booking createBooking(String bookingsName, String bookingsEmail, String bookingsDate, Integer bookingsAmount) throws IOException {

        Booking booking = new Booking(bookingsName, bookingsEmail,
                                        bookingsDate, bookingsAmount,
                                        bookingsStatus);

        bookingRepository.save(booking);

        return booking;
    }

}
