package stellunia.Bookings_App.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import stellunia.Bookings_App.bookings.Booking;

import java.time.LocalDateTime;

// The DTO response object that bookings utilise
@Data
@AllArgsConstructor
public class BookingResponseDTO extends RepresentationModel<BookingResponseDTO> {
    private String bookingsId;
    private String bookingsName;
    private String bookingsEmail;
    private LocalDateTime bookingsDate;
    private Integer bookingsAmount;
    private String bookingsStatus;

    public static BookingResponseDTO fromModel(Booking booking) {
        return new BookingResponseDTO(
                booking.getBookingId().toString(),
                booking.getBookingName(),
                booking.getBookingEmail(),
                booking.getBookingDate(),
                booking.getBookingAmount(),
                booking.getBookingStatus().toString()
        );
    }

}
