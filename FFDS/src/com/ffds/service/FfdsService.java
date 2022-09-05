package com.ffds.service;

import com.ffds.order.Order;
import com.ffds.restaurant.Restaurant;
import com.ffds.user.User;
import kotlin.Pair;

import java.util.*;

public class FfdsService {

    HashMap<String, Restaurant> restaurant_data = new HashMap<>();
    HashMap<String, List<Restaurant>> restaurant_data_location = new HashMap<>();
    HashMap<String, User> user_data = new HashMap<>();
    HashMap<String, List<Order>> order_data = new HashMap<>();
    private String locationOfCustomer = "";
    private String personLoggedIn = "";


    public void register_user(String name, String gender, String phoneNumber, String pinCode){
        User user = new User();
        user.setGender(gender);
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        user.setPinCode(pinCode);
        user_data.put(phoneNumber, user);
        System.out.println("Congratulation " + name + " you are now registered with Flipkart Food App");
    }

    public void register_restaurant(String restaurantName, String serviceablePinCodes, String foodItemName, int foodItemPrice, int initialQuantity){
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName(restaurantName);
        restaurant.setFoodItemName(foodItemName);
        restaurant.setFoodItemPrice(foodItemPrice);
        restaurant.setInitialQuantity(initialQuantity);
        restaurant.setServiceablePinCodes(serviceablePinCodes);
        restaurant_data.put(restaurantName,restaurant);
        String[] regions = serviceablePinCodes.split("/");
        for(String region : regions){
            if(restaurant_data_location.get(region) == null){
                List<Restaurant> restaurantList = new ArrayList<>();
                restaurantList.add(restaurant);
                restaurant_data_location.put(region, restaurantList);
            }else{
                List<Restaurant> restaurantList = restaurant_data_location.get(region);
                restaurantList.add(restaurant);
                restaurant_data_location.put(region, restaurantList);
            }
        }
        System.out.println("Restaurant " + restaurantName + " is now registered with Flipkart Food App");
    }

    public void update_quantity(String restaurantName, int quantityToAdd){
        Restaurant restaurant = restaurant_data.get(restaurantName);
        if(restaurant!=null){
            restaurant.setInitialQuantity(restaurant.getInitialQuantity()+quantityToAdd);
            restaurant_data.put(restaurantName,restaurant);
            System.out.println("You Successfully Updated" + restaurantName + "Restaurant Initial Quantity");

        }else{
            System.out.println("Oops Restaurant " + restaurantName + " is not registered with Flipkart Food App");
        }
    }

    public void create_review(String restaurantName, int rating, String comments){
        Restaurant restaurant = restaurant_data.get(restaurantName);
        if(restaurant!=null){
            restaurant.setRatings(new Pair<>(rating, comments));
            restaurant_data.put(restaurantName, restaurant);
            System.out.println("You have successfully rated " + restaurantName + "Restaurant");
        }else{
            System.out.println("Oops Restaurant " + restaurantName + " is not registered with Flipkart Food App");
        }
    }

    public void user_login(String phoneNumber){
        User user = user_data.get(phoneNumber);
        if(user != null){
            locationOfCustomer = user.getPinCode();
            personLoggedIn = phoneNumber;
            System.out.println("You are now logged in!!!");
        }else{
            System.out.println("Oops " + phoneNumber + " is not registered with Flipkart Food App, Kindly first registered with Flipkart Food App");
        }
    }

    public void show_restaurant(String parameter){
        List<Restaurant> restaurantList = restaurant_data_location.get(locationOfCustomer);
        if(restaurantList != null){
            if(parameter == "price"){
                Collections.sort(restaurantList, new Comparator<Restaurant>() {
                    @Override
                    public int compare(Restaurant o1, Restaurant o2) {
                        return o2.getFoodItemPrice()-o1.getFoodItemPrice();
                    }
                });
                for(Restaurant restaurant : restaurantList){
                    System.out.println(restaurant.getRestaurantName()+", " + restaurant.getFoodItemName());
                }
            }else{
                Collections.sort(restaurantList, new Comparator<Restaurant>() {
                    @Override
                    public int compare(Restaurant o1, Restaurant o2) {
                        return o2.getRatings().component1()-o1.getRatings().component1();
                    }
                });
                for(Restaurant restaurant : restaurantList){
                    System.out.println(restaurant.getRestaurantName()+", " + restaurant.getFoodItemName());
                }

            }
        }else{
            System.out.println("Oops No Restaurants in your Area");

        }
    }

    public void place_order(String restaurantName, int quantity){
        Restaurant restaurant = restaurant_data.get(restaurantName);
        if(restaurant == null){
            System.out.println("Oops No Restaurants in your Area");
        }else{
            if(restaurant.getServiceablePinCodes().contains(locationOfCustomer)){
                if(restaurant.getInitialQuantity() >= quantity){
                    Order order = new Order();
                    order.setRestaurantName(restaurantName);
                    order.setOrderQuantity(quantity);
                    order.setFoodItemName(restaurant_data.get(restaurantName).getFoodItemName());
                    order.setFoodItemPrice(restaurant_data.get(restaurantName).getFoodItemPrice());
                    if(order_data.get(personLoggedIn) == null) {
                        List<Order> orderList = new ArrayList<>();
                        orderList.add(order);
                        order_data.put(personLoggedIn, orderList);
                    }else{
                        List<Order> orderList = order_data.get(personLoggedIn);
                        orderList.add(order);
                        order_data.put(personLoggedIn, orderList);
                    }
                    System.out.println("Hurray order placed");
                }else{
                    System.out.println("Oops cannot Process your Order");
                }
            }else{
                System.out.println("Oops No Restaurants in your Area");
            }
        }
    }

    public void update_location(String restaurantName, String location) {
        Restaurant restaurant = restaurant_data.get(restaurantName);
        if (restaurant == null) {
            System.out.println("Oops No Restaurants in your Area");
        } else{
            String[] regions = location.split("/");
            for(String region : regions){
                if(restaurant_data_location.get(region) == null){
                    List<Restaurant> restaurantList = new ArrayList<>();
                    restaurantList.add(restaurant);
                    restaurant_data_location.put(region, restaurantList);
                }else{
                    List<Restaurant> restaurantList = restaurant_data_location.get(region);
                    Optional<Restaurant> currentRestaurant = restaurantList.stream().filter(x -> x.getRestaurantName().equalsIgnoreCase(restaurantName)).findFirst();
                    if(currentRestaurant == null){
                        restaurantList.add(restaurant);
                    }
                    restaurant_data_location.put(region, restaurantList);
                }
            }
            restaurant.setServiceablePinCodes(location);
            System.out.println("Restaurants Area Updated");
        }
    }
    public void order_history(String user){
        List <Order> orderList = order_data.get(user);
        if(orderList == null){
            System.out.println("No Previous orders");
        }else{
            for(Order order: orderList){
                System.out.println(order.getFoodItemName() + " " + order.getRestaurantName() + " " + order.getFoodItemPrice() + " " + order.getOrderQuantity());
            }
        }
    }
}
