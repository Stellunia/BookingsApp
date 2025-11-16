package stellunia.Bookings_App.bookings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findByBookingName(String name);
    List<Booking> findByBookingDate(LocalDateTime bookingDate);
    Optional<Booking> findByBookingId(UUID bookingsId);
}
