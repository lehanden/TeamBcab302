import com.example.dinnerreserver.model.Booking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookingTest {

    private Booking booking;

    @BeforeEach
    void setUp() {
        // Create a new booking instance for each test
        booking = new Booking(1, 101, 4, "2024-10-24 19:00");
    }

    @Test
    public void testAllBookingFields() {
        assertEquals(1, booking.getUserId());
        assertEquals(101, booking.getRestaurantId());
        assertEquals(4, booking.getNumberOfPeople());
        assertEquals("2024-10-24 19:00", booking.getBookingTime());
    }

    @Test
    public void testSetAndGetBookingId() {
        booking.setBookingId(10);
        assertEquals(10, booking.getBookingId());
    }

    @Test
    public void testSetAndGetUserId() {
        booking.setUserId(2);
        assertEquals(2, booking.getUserId());
    }

    @Test
    public void testSetAndGetRestaurantId() {
        booking.setRestaurantId(202);
        assertEquals(202, booking.getRestaurantId());
    }

    @Test
    public void testSetAndGetNumberOfPeople() {
        booking.setNumberOfPeople(6);
        assertEquals(6, booking.getNumberOfPeople());
    }

    @Test
    public void testSetAndGetBookingTime() {
        booking.setBookingTime("2024-10-25 20:00");
        assertEquals("2024-10-25 20:00", booking.getBookingTime());
    }
}