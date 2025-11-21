package stellunia.Bookings_App.bookings;

// Status shenanigans, only really utilises PENDING,
// but wanted to add a feature that made it so that ones that got cancelled got the "CANCELLED" one instead of being removed
// - as well as add the ability to have ones that have passed the due-date get reclassified as "CONFIRMED"
public enum Status {
    PENDING, CONFIRMED, CANCELLED
}
