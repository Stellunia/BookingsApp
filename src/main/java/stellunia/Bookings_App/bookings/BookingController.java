package stellunia.Bookings_App.bookings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stellunia.Bookings_App.dto.BookingResponseDTO;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@RestController
@RequestMapping("/bookingsapp/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/createBooking")
    public ResponseEntity<Object> createBooking(@RequestParam("bookingName")String bookingName,
                                                @RequestParam("bookingEmail")String bookingEmail,
                                                @RequestParam("bookingDate")String bookingDate,
                                                @RequestParam("bookingAmount")Integer bookingAmount) {
        try {
            if (bookingName.isEmpty() || bookingEmail.isEmpty()) {
                throw new BookingIncompleteException("Please enter a name and E-mail.");}

            if (bookingDate.isEmpty()) {
                throw new BookingIncompleteException("Please select a date.");}

            if (bookingAmount.toString().isEmpty()) {
                throw new BookingIncompleteException("Please enter how many will be present.");}

            Booking booking = new Booking();
            booking.setBookingName(bookingName);
            booking.setBookingEmail(bookingEmail);
            booking.setBookingDate(LocalDateTime.parse(bookingDate));
            booking.setBookingAmount(bookingAmount);

            Booking savedBooking = bookingService.createBooking(booking);
            return ResponseEntity.ok(savedBooking);

/*            Booking booking = bookingService.createBooking(bookingName, bookingEmail, bookingDate, bookingAmount *//*bookingStatus*//*);

            return ResponseEntity.ok(new BookingResponseDTO(
                    booking.getBookingName(),
                    booking.getBookingEmail(),
                    booking.getBookingDate(),
                    booking.getBookingAmount()
            ));*/

        } catch (BookingIncompleteException | IllegalArgumentException | IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());//throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/updateBooking/{id}")
    public ResponseEntity<Object> updateBooking(@RequestParam("bookingId")String bookingId,
                                                @RequestParam("bookingName")String bookingName,
                                                @RequestParam("bookingEmail")String bookingEmail,
                                                @RequestParam("bookingDate")String bookingDate,
                                                @RequestParam("bookingAmount")Integer bookingAmount) {
        try {
            UUID id = UUID.fromString(bookingId);
            Booking booking = new Booking();
            booking.setBookingName(bookingName);
            booking.setBookingEmail(bookingEmail);
            booking.setBookingDate(LocalDateTime.parse(bookingDate));
            booking.setBookingAmount(bookingAmount);

            Booking savedBooking = bookingService.updateBooking(id, booking);
            return ResponseEntity.ok(savedBooking);

        } catch (BookingIncompleteException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());//throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/nameBookings")
    public Stream<BookingResponseDTO> getNameBookings(
            @RequestParam("bookingId")String bookingId) {
        return bookingService.getNameBookings(bookingId).stream().map(BookingResponseDTO::fromModel);
    }

    @GetMapping("/allBookings")
    public Stream<BookingResponseDTO> getBookings() {
        return bookingService.getAllBookings().stream().map(BookingResponseDTO::fromModel);
    }

    @GetMapping("/{id}")
    public Optional<Booking> getBookingById(@PathVariable UUID id) {
        return bookingService.getBookingById(id);
    }

    @DeleteMapping("/cancelBooking/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable UUID id){
        try {
            bookingService.deleteBooking(id);
            return ResponseEntity.ok().body("Booking with ID: " + id + " cancelled.");
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error cancelling booking: " + id + ".\nWith error message: " + e.getMessage());
        }
    }


    /*API:et ska hantera följande endpoints:
    - GET /bookings för att hämta alla bokningar                - DONE
    - GET /bookings/:id för att hämta en specifik bokning       - DONE
    - POST /bookings för att skapa en ny bokning                - WIP
    - PUT /bookings/:id för att uppdatera en befintlig bokning  -
    - DELETE /bookings/:id för att ta bort en bokning           - DONE (But still needs to have it move from whatever it's at into a different one)
    */
// Scale bookings by status? PENDING, CANCELLED, CONFIRMED
}
