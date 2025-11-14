package stellunia.Bookings_App.bookings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import stellunia.Bookings_App.dto.BookingResponseDTO;

import java.io.IOException;

@RestController
@RequestMapping("/bookingsapp/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/createBooking")
    public ResponseEntity<Object> createBooking(@RequestParam("name")String bookingName,
                                                @RequestParam("Email")String bookingEmail,
                                                @RequestParam("Date")String bookingDate,
                                                @RequestParam("Amount")Integer bookingAmount) {
        try {
            if (bookingName.isEmpty()) {
                throw new BookingsIncompleteException("Cannot create booking.");
            }

            //StorageFolder folderToStore = storageFolderService.getFolderByName(storageFolder); // here is where it fails? - changed .toString() to instead get the folder name
            //StorageUser ownerOfFile = storageUserService.getUserByName(storageUser);
            //StorageFile storageFile = storageFileService.uploadFile(multipartFile, folderToStore, storageUser);
            Booking booking = BookingService.createBooking(bookingName, bookingEmail, bookingDate, bookingAmount);

/*            String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/storageapp/storageuser/files/download/")
                    .path(storageFile.getFileId().toString())
                    .toUriString();*/

            return ResponseEntity.ok(new BookingResponseDTO(
                    booking.getBookingName(),
                    /*downloadUrl,*/
                    booking.getBookingEmail(),
                    booking.getBookingDate(),
                    booking.getBookingAmount()
            ));

        } catch (IllegalArgumentException | IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());//throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    /*API:et ska hantera följande endpoints:
    - GET /bookings för att hämta alla bokningar
    - GET /bookings/:id för att hämta en specifik bokning
    - POST /bookings för att skapa en ny bokning
    - PUT /bookings/:id för att uppdatera en befintlig bokning
    - DELETE /bookings/:id för att ta bort en bokning
    */

}
