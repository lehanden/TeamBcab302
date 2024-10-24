import com.example.dinnerreserver.model.*;

import com.example.dinnerreserver.model.RestaurantManager;
import com.example.dinnerreserver.model.SharedData;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestaurantTest {
    private RestaurantManager restaurantManager;
    private Restaurant[] restaurants = {
            new Restaurant(1, "San Kai Japanese Restaurant", "164 Grey St, South Brisbane", "Japanese classics like sushi, tempura & gyoza served in a chic dining room with sidewalk seating.", 3.0F, "Restaurant1.jpg", "https://quandoo-assets-partner.s3-eu-west-1.amazonaws.com/partner/uploads/d52bde99-b012-4cf1-ade2-3a243296e54b/MD-document-ad724adf-6a9b-47af-9ec0-4a579a1f3abd.pdf"),
            new Restaurant(2, "Olé Restaurant", "Shop/B12 Little Stanley St, South Brisbane", "Vibrant Spanish eatery with tapas, sangria bar and stylish decor, plus an intricate wooden ceiling.", 4.2F, "Restaurant2.jpeg", "https://olerestaurant.com.au/menu/"),
            new Restaurant(3, "Geláre South Bank", "3/164 Grey St, South Brisbane QLD 4101", "Ice cream, smoothies and low-fat frozen yoghurt, plus classic breakfasts, in a bright cafe chain.", 3.9F, "Restaurant3.jpg", "https://gelare.com.au/menu/")
    };


    @BeforeEach
    public void setUp(){
        restaurantManager = new RestaurantManager(new MockRestaurantDAO());
    }

    @Test
    public void testSearchForOneRestaurant(){
        restaurantManager.addRestaurant(restaurants[0]);
        List<Restaurant> restaurants = restaurantManager.searchRestaurants("San Kai Japanese Restaurant");
        assertEquals(1, restaurants.size());
        assertEquals(this.restaurants[0], restaurants.get(0));
    }
    @Test
    public void testSearchNoResults(){
        for(Restaurant restaurant : restaurants){
            restaurantManager.addRestaurant(restaurant);
        }
        List<Restaurant> restaurants = restaurantManager.searchRestaurants("McDonalds");
        assertEquals(0, restaurants.size());
    }
    @Test
    public void testSearchEmptyQuery(){
        for(Restaurant restaurant : restaurants){
            restaurantManager.addRestaurant(restaurant);
        }
        List<Restaurant> restaurants = restaurantManager.searchRestaurants("");
        assertEquals(3, restaurants.size());
    }
    @Test
    public void testSearchNullQuery(){
        for(Restaurant restaurant : restaurants){
            restaurantManager.addRestaurant(restaurant);
        }
        List<Restaurant> restaurants = restaurantManager.searchRestaurants(null);
        assertEquals(3, restaurants.size());
    }
    @Test
    public void testSearchCaseInsensitive(){
        for(Restaurant restaurant : restaurants){
            restaurantManager.addRestaurant(restaurant);
        }
        List<Restaurant> restaurants = restaurantManager.searchRestaurants("san kai japanese restaurant");
        assertEquals(1, restaurants.size());
        for(Restaurant restaurant : restaurants){
            assertTrue(restaurant.getName().equalsIgnoreCase("San Kai Japanese Restaurant"));
        }
    }
    @Test
    public void testSearchPartialQuery(){
        for(Restaurant restaurant : restaurants){
            restaurantManager.addRestaurant(restaurant);
        }
        List<Restaurant> restaurants = restaurantManager.searchRestaurants("Restaurant");
        assertEquals(2, restaurants.size());
        assertTrue(restaurants.get(0).getName().equals("San Kai Japanese Restaurant"));
        assertTrue(restaurants.get(1).getName().equals("Olé Restaurant"));
    }

    @Test
    public void testSortByAlphabetical() {
        for (Restaurant restaurant : restaurants) {
            restaurantManager.addRestaurant(restaurant);
        }
        List<Restaurant> sortedRestaurants = restaurantManager.sortRestaurantsAlphabetically(restaurantManager.getAllRestaurants());
        assertEquals("Geláre South Bank", sortedRestaurants.get(0).getName());
        assertEquals("Olé Restaurant", sortedRestaurants.get(1).getName());
        assertEquals("San Kai Japanese Restaurant", sortedRestaurants.get(2).getName());
    }
    @Test
    public void testSortByAlphabeticalWithNoResults() {
        List<Restaurant> emptyList = restaurantManager.sortRestaurantsAlphabetically(new ArrayList<>());

        // Verify that the result is still an empty list
        assertEquals(0, emptyList.size());
    }

    @Test
    public void testSortByRating() {
        for (Restaurant restaurant : restaurants) {
            restaurantManager.addRestaurant(restaurant);
        }
        List<Restaurant> sortedRestaurants = restaurantManager.sortRestaurantsByRating(restaurantManager.getAllRestaurants());
        assertEquals("Olé Restaurant", sortedRestaurants.get(0).getName()); // Highest rating first
        assertEquals("Geláre South Bank", sortedRestaurants.get(1).getName());
        assertEquals("San Kai Japanese Restaurant", sortedRestaurants.get(2).getName()); // Lowest rating last
    }
    @Test
    public void testSortByRatingWithNoResults() {
        List<Restaurant> emptyList = restaurantManager.sortRestaurantsByRating(new ArrayList<>());
        assertEquals(0, emptyList.size());
    }

    @Test
    public void testSearchAndSortByRating() {
        for (Restaurant restaurant : restaurants) {
            restaurantManager.addRestaurant(restaurant);
        }
        List<Restaurant> searchResults = restaurantManager.searchRestaurants("Restaurant");
        List<Restaurant> sortedResults = restaurantManager.sortRestaurantsByRating(searchResults);
        assertEquals(2, sortedResults.size());
        assertEquals("Olé Restaurant", sortedResults.get(0).getName()); // Sorted by rating
        assertEquals("San Kai Japanese Restaurant", sortedResults.get(1).getName());
    }
    @Test
    public void testSearchAndSortByAlphabetical() {
        for (Restaurant restaurant : restaurants) {
            restaurantManager.addRestaurant(restaurant);
        }
        List<Restaurant> searchResults = restaurantManager.searchRestaurants("Restaurant");
        List<Restaurant> sortedResults = restaurantManager.sortRestaurantsAlphabetically(searchResults);
        assertEquals(2, sortedResults.size());
        assertEquals("Olé Restaurant", sortedResults.get(0).getName()); // Sorted alphabetically
        assertEquals("San Kai Japanese Restaurant", sortedResults.get(1).getName());
    }
    @Test
    public void testDeleteRestaurant() {
        for (Restaurant restaurant : restaurants) {
            restaurantManager.addRestaurant(restaurant);
        }

        // Delete one restaurant
        restaurantManager.deleteRestaurant(restaurants[0]);

        // Verify the restaurant was deleted
        List<Restaurant> remainingRestaurants = restaurantManager.getAllRestaurants();
        assertEquals(2, remainingRestaurants.size()); // There should be only 2 restaurants now
        assertFalse(remainingRestaurants.contains(restaurants[0])); // Ensure the deleted restaurant is no longer present
    }

    @Test
    public void testDeleteNonExistentRestaurant() {
        for (Restaurant restaurant : restaurants) {
            restaurantManager.addRestaurant(restaurant);
        }

        // Attempt to delete a restaurant that was never added
        Restaurant nonExistentRestaurant = new Restaurant(4, "Non Existent Restaurant", "123 Nowhere St", "This restaurant does not exist.", 5.0F, "NonExistent.jpg", "https://nonexistent.com/menu");
        restaurantManager.deleteRestaurant(nonExistentRestaurant);

        // Verify that no restaurants were deleted
        List<Restaurant> remainingRestaurants = restaurantManager.getAllRestaurants();
        assertEquals(3, remainingRestaurants.size()); // There should still be 3 restaurants
    }

}
class SharedDataTest {

    @BeforeEach
    public void resetSharedData() {
        // Reset the selected restaurant to null before each test
        SharedData.getInstance().setSelectedRestaurant(null);
    }

    @Test
    public void testSingletonInstance() {
        // Verify that only one instance is created
        SharedData instance1 = SharedData.getInstance();
        SharedData instance2 = SharedData.getInstance();
        assertSame(instance1, instance2, "SharedData should return the same instance");
    }

    @Test
    public void testSetSelectedRestaurant() {
        // Create a new restaurant and set it as selected
        Restaurant restaurant = new Restaurant(1, "Test Restaurant", "123 Test St", "Test Description", 4.5F, "test.jpg", "https://test.com");
        SharedData sharedData = SharedData.getInstance();
        sharedData.setSelectedRestaurant(restaurant);

        // Verify that the selected restaurant is correctly set
        assertEquals(restaurant, sharedData.getSelectedRestaurant(), "Selected restaurant should match the one set");
    }

    @Test
    public void testGetSelectedRestaurantInitiallyNull() {
        // Verify that initially the selected restaurant is null
        SharedData sharedData = SharedData.getInstance();
        assertNull(sharedData.getSelectedRestaurant(), "Initially, the selected restaurant should be null");
    }

    @Test
    public void testUpdateSelectedRestaurant() {
        // Set a restaurant, then update it to a new restaurant and verify
        Restaurant restaurant1 = new Restaurant(1, "Test Restaurant 1", "123 Test St", "Test Description 1", 4.5F, "test1.jpg", "https://test1.com");
        Restaurant restaurant2 = new Restaurant(2, "Test Restaurant 2", "456 Test Ave", "Test Description 2", 3.5F, "test2.jpg", "https://test2.com");

        SharedData sharedData = SharedData.getInstance();
        sharedData.setSelectedRestaurant(restaurant1);
        assertEquals(restaurant1, sharedData.getSelectedRestaurant(), "The first restaurant should be selected");

        // Update the selected restaurant
        sharedData.setSelectedRestaurant(restaurant2);
        assertEquals(restaurant2, sharedData.getSelectedRestaurant(), "The selected restaurant should be updated to the new one");
    }
}
class SqliteRestaurantDAOTest extends SqliteRestaurantDAO {

    private SqliteRestaurantDAO restaurantDAO;
    private Connection connection;

    @BeforeEach
    public void setUp() throws Exception {
        // Use an in-memory SQLite database for testing
        connection = DriverManager.getConnection("jdbc:sqlite::memory:");
        restaurantDAO = new SqliteRestaurantDAO() {
            protected Connection getConnection() {
                return connection; // Return the in-memory connection for testing
            }
        };
        createTable(); // You can now access the protected createTable() method
        restaurantDAO.insertSampleData();
    }

    @Test
    public void testAddRestaurant() {
        // Create and add a new restaurant
        Restaurant newRestaurant = new Restaurant(0, "Test Restaurant", "123 Test St", "Test description", 4.0F, "test.jpg", "https://test.com");
        restaurantDAO.addRestaurant(newRestaurant);

        // Fetch the restaurant from the database
        Restaurant fetchedRestaurant = restaurantDAO.getRestaurant(newRestaurant.getId());

        // Compare each field individually instead of the whole object
        assertEquals(newRestaurant.getId(), fetchedRestaurant.getId());
        assertEquals(newRestaurant.getName(), fetchedRestaurant.getName());
        assertEquals(newRestaurant.getAddress(), fetchedRestaurant.getAddress());
        assertEquals(newRestaurant.getDescription(), fetchedRestaurant.getDescription());
        assertEquals(newRestaurant.getRating(), fetchedRestaurant.getRating());
        assertEquals(newRestaurant.getImageSource(), fetchedRestaurant.getImageSource());
        assertEquals(newRestaurant.getMenuSource(), fetchedRestaurant.getMenuSource());
    }

    @Test
    public void testDeleteRestaurant() {
        Restaurant newRestaurant = new Restaurant(0, "Test Restaurant", "123 Test St", "Test description", 4.0F, "test.jpg", "https://test.com");
        restaurantDAO.addRestaurant(newRestaurant);
        int id = newRestaurant.getId();

        // Delete the restaurant
        restaurantDAO.deleteRestaurant(newRestaurant);

        // Verify the restaurant was deleted
        Restaurant fetchedRestaurant = restaurantDAO.getRestaurant(id);
        assertNull(fetchedRestaurant, "Restaurant should be deleted");
    }


    @Test
    public void testGetRestaurant() {
        // Add a new restaurant and fetch it
        Restaurant newRestaurant = new Restaurant(0, "Test Restaurant", "123 Test St", "Test description", 4.0F, "test.jpg", "https://test.com");
        restaurantDAO.addRestaurant(newRestaurant);

        // Fetch the restaurant from the database
        Restaurant fetchedRestaurant = restaurantDAO.getRestaurant(newRestaurant.getId());

        // Compare each field instead of the entire object
        assertEquals(newRestaurant.getId(), fetchedRestaurant.getId());
        assertEquals(newRestaurant.getName(), fetchedRestaurant.getName());
        assertEquals(newRestaurant.getAddress(), fetchedRestaurant.getAddress());
        assertEquals(newRestaurant.getDescription(), fetchedRestaurant.getDescription());
        assertEquals(newRestaurant.getRating(), fetchedRestaurant.getRating());
        assertEquals(newRestaurant.getImageSource(), fetchedRestaurant.getImageSource());
        assertEquals(newRestaurant.getMenuSource(), fetchedRestaurant.getMenuSource());
    }
}

