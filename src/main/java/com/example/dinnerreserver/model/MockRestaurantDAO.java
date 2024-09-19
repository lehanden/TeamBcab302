package com.example.dinnerreserver.model;
import java.util.ArrayList;
import java.util.List;
public class MockRestaurantDAO implements IRestaurantDAO{
    private ArrayList<Restaurant> restaurants = new ArrayList<>();
    private int autoIncrementedId = 0;
//    public MockRestaurantDAO() {
//        addRestaurant(new Restaurant(1, "San Kai Japanese Restaurant", "164 Grey St, South Brisbane", "Japanese classics like sushi, tempura & gyoza served in a chic dining room with sidewalk seating.", 3.0F));
//        addRestaurant(new Restaurant(2, "Olé Restaurant", "Shop/B12 Little Stanley St, South Brisbane", "Vibrant Spanish eatery with tapas, sangria bar and stylish decor, plus an intricate wooden ceiling.", 4.2F));
//        addRestaurant(new Restaurant(3, "Geláre South Bank", "3/164 Grey St, South Brisbane QLD 4101", "Ice cream, smoothies and low-fat frozen yoghurt, plus classic breakfasts, in a bright cafe chain.", 3.9F));               ;
//    }

    @Override
    public void addRestaurant(Restaurant restaurant) {
        restaurant.setId(autoIncrementedId);
        autoIncrementedId++;
        restaurants.add(restaurant);
    }

    @Override
    public void updateRestaurant(Restaurant restaurant){
        for(int i = 0; i < restaurants.size(); i++){
            if(restaurants.get(i).getId() == restaurant.getId()){
                restaurants.set(i, restaurant);
                break;
            }
        }
    }

    @Override
    public void deleteRestaurant(Restaurant restaurant) {
        restaurants.remove(restaurant);
    }
    @Override
    public Restaurant getRestaurant(int id) {
        for(Restaurant restaurant : restaurants) {
            if(restaurant.getId() == id){
                return restaurant;
            }
        }
        return null;
    }
    @Override
    public List<Restaurant> getAllRestaurants() {
        return new ArrayList<>(restaurants);
    }
}