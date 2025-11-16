package stellunia.Bookings_App.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import stellunia.Bookings_App.bookings.Status;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateBookingDTO {
    public UUID bookingId;
    public String bookingName;
    public String bookingEmail;
    public String bookingDate;
    public Integer bookingAmount;
    public Status status;
}
