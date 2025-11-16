package stellunia.Bookings_App.bookings;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookingService {

    @Autowired
    private final BookingRepository bookingRepository;

    @Transactional
    public Booking createBooking(Booking booking) throws IOException {
/*        Booking booking = new Booking(bookingsName, bookingsEmail,
                                        bookingsDate, bookingsAmount);*/
        if (booking.getBookingId() == null) {
            booking.setBookingId(UUID.randomUUID());
        }
        if (booking.getBookingStatus() == null) {
            booking.setBookingStatus(Status.PENDING);
        }

        return bookingRepository.save(booking);
    }

    @Transactional
    public Booking updateBooking(UUID id, Booking updatedBooking) {
        return bookingRepository.findByBookingId(id)
                .map(existing -> {
                    if (updatedBooking.getBookingName() != null) {
                        existing.setBookingName(updatedBooking.getBookingName());
                    }
                    if (updatedBooking.getBookingEmail() != null) {
                        existing.setBookingEmail(updatedBooking.getBookingEmail());
                    }
                    if (updatedBooking.getBookingDate() != null) {
                        existing.setBookingDate(updatedBooking.getBookingDate());
                    }
                    if (updatedBooking.getBookingAmount() != null) {
                        existing.setBookingAmount(updatedBooking.getBookingAmount());
                    }
                    if (updatedBooking.getBookingStatus() != null) {
                        existing.setBookingStatus(updatedBooking.getBookingStatus());
                    }
                    return bookingRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + id));
    }

    public Optional<Booking> getBookingById(UUID id) {
        return bookingRepository.findByBookingId(id);
    }

    public List<Booking> getNameBookings(String bookingName) {
        return bookingRepository.findByBookingName(bookingName);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public void deleteBooking(UUID id){
        Booking booking = bookingRepository.findById(id).orElseThrow(() ->new RuntimeException("Booking not found."));
        bookingRepository.delete(booking);
    }

}
