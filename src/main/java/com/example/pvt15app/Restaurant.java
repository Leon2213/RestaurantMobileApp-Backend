package com.example.pvt15app;

import java.util.List;

public class Restaurant {

    private String name;
    private double longitude;
    private double latitude;
    private Boolean isOpen;
    private String address;
    private String restaurantId;
    private List<String> foodTypes;
    private List<String> reviews;
    private String rating;
    private String photoReference;

    public Restaurant() {
    }

    public Restaurant(String name, double longitude, double latitude, Boolean isOpen, String address) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.isOpen = isOpen;
        this.address = address;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Boolean isOpen() {
        if (isOpen == null) {
            return false;
        } else {
            return isOpen;
        }
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId){
        this.restaurantId = restaurantId;
    }

    public String getRating() {
        return rating;
    }

    public void addFoodtypes(List<String> foodTypes){
        this.foodTypes = foodTypes;
    }

    public List<String> getFoodTypes() {
        return foodTypes;
    }

    public List<String> getReviews(){
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setPhotoReference(String photoRefString) {
        this.photoReference = photoRefString;
    }

    public String getPhotoReference(){
        return photoReference;
    }



    @Override
    public String toString() {
        return "Restaurant: " + "\n" +
                "name: " + name + " \n" +
                "longitude: " + longitude + " \n" +
                "latitude: " + latitude + " \n" +
                "isOpen: " + isOpen + " \n" +
                "address: " + address + " \n" +
                "restaurant-id " + restaurantId;

    }


}
//