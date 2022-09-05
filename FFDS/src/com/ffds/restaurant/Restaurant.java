package com.ffds.restaurant;


import kotlin.Pair;

public class Restaurant {

    private String restaurantName;
    private String serviceablePinCodes;
    private String foodItemName;
    private int foodItemPrice;
    private int initialQuantity;
    private Pair<Integer, String> ratings ;


    public Pair<Integer, String> getRatings() {
        return ratings;
    }

    public void setRatings(Pair<Integer, String> ratings) {
        this.ratings = ratings;
    }


    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getServiceablePinCodes() {
        return serviceablePinCodes;
    }

    public void setServiceablePinCodes(String serviceablePinCodes) {
        this.serviceablePinCodes = serviceablePinCodes;
    }

    public String getFoodItemName() {
        return foodItemName;
    }

    public void setFoodItemName(String foodItemName) {
        this.foodItemName = foodItemName;
    }

    public int getFoodItemPrice() {
        return foodItemPrice;
    }

    public void setFoodItemPrice(int foodItemPrice) {
        this.foodItemPrice = foodItemPrice;
    }

    public int getInitialQuantity() {
        return initialQuantity;
    }

    public void setInitialQuantity(int initialQuantity) {
        this.initialQuantity = initialQuantity;
    }

}
