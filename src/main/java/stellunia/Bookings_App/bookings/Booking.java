package stellunia.Bookings_App.bookings;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

//@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "bookings")
@Data
public class Booking {

    @Id
    @Column(name = "booking_id")
    private UUID bookingId;

    @Column(name = "booking_name")
    private String bookingName;

    //@ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "booking_email")
    private String bookingEmail;

    //@ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "booking_date")
    private LocalDateTime bookingDate;

    @Column(name = "booking_amount")
    private Integer bookingAmount;

    @Column(name = "booking_status")
    private Status bookingStatus = Status.PENDING;



    public Booking() {this.bookingId = UUID.randomUUID();}

    @Autowired
    public Booking(String bookingName, String bookingEmail, LocalDateTime bookingDate, Integer bookingAmount) {
        this.bookingId = UUID.randomUUID();
        this.bookingName = bookingName;
        this.bookingEmail = bookingEmail;
        this.bookingDate = bookingDate;
        this.bookingAmount = bookingAmount;
        this.bookingStatus = bookingStatus;
    }

    @Override
    public String toString() { return super.toString(); }

/*  #Should it have the ability to book from a date to another? Use these, Ig
    @Column(name = "bookings_from_date")
    private String bookingsFromDate;

    @Column(name = "bookings_to_date")
    private String bookingsToDate;
 */

    /* Bokningar ska minst bestå av:
     * Namn (på person som bokar)
     * E-postadress
     * Datum och tid
     * Antal personer
     * Status (PENDING, CONFIRMED, CANCELLED)*/
}
