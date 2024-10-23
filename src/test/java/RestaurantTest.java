import com.example.dinnerreserver.model.MockRestaurantDAO;
import com.example.dinnerreserver.model.Restaurant;

import com.example.dinnerreserver.model.RestaurantManager;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

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


}
