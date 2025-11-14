package stellunia.Bookings_App.bookings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    Optional<Booking> findByBookingsName(String name);
    List<Booking> findByBookingsDate(String date);
    Optional<Booking> findByBookingsId(UUID bookingsId);
}
