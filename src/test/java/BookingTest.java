import com.example.dinnerreserver.model.Booking;
import com.example.dinnerreserver.model.SqliteBookingDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookingTest {

    private Booking booking;
    private SqliteBookingDAO bookingDAO;
    private Connection connection;

    @BeforeEach
    void setUp() throws Exception {
        // Initialize the Booking instance for model tests
        booking = new Booking(1, 101, 4, "2024-10-24 19:00");

        // Set up the database connection for the SqliteBookingDAO tests
        connection = DriverManager.getConnection("jdbc:sqlite::memory:");

        // Initialize the bookingDAO with the in-memory connection
        bookingDAO = new SqliteBookingDAO();

        // Manually set the connection for testing purposes (assuming SqliteBookingDAO uses the connection field internally)

        // Set up the bookings table for testing
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS bookings (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id INTEGER NOT NULL," +
                "restaurant_id INTEGER NOT NULL," +
                "number_of_people INTEGER NOT NULL," +
                "booking_time TEXT NOT NULL" +
                ")");

        // Clear the bookings table before each test to avoid cumulative data
        statement.execute("DELETE FROM bookings");
    }

    @AfterEach
    public void tearDown() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    // Booking model tests
    @Test
    public void testConstructor() {
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

    // SqliteBookingDAO tests
    @Test
    public void testAddBooking() {
        Booking newBooking = new Booking(1, 101, 4, "2024-10-25 19:00");
        bookingDAO.addBooking(newBooking);

        // Verify that booking ID was generated
        assertTrue(newBooking.getBookingId() > 0);
    }

    @Test
    public void testCountBookingsForTimeSlotWithNoBookings() {
        int count = bookingDAO.countBookingsForTimeSlot(999, "2024-10-25 19:00");
        assertEquals(0, count); // No bookings should return 0
    }

    @Test
    public void testDeleteBooking() {
        Booking newBooking = new Booking(1, 101, 4, "2024-10-25 19:00");
        bookingDAO.addBooking(newBooking);

        // Delete the booking
        bookingDAO.deleteBooking(newBooking);

        // Ensure that no booking exists with this ID anymore
        Booking deletedBooking = bookingDAO.getBooking(newBooking.getBookingId());
        assertNull(deletedBooking); // The booking should be null after deletion
    }
}