package com.example.pvt15app;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RestaurantDataHolder {
    @Id
    private String placeid;

    private String restaurantName;

    @ElementCollection
    private List<String> categories = new ArrayList<>();

    @ElementCollection
    private List<String> reviews = new ArrayList<>();

    public String getPlaceid() {
        return placeid;
    }

    public void setPlaceid(String placeid) {
        this.placeid = placeid;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void addReview(String review) {
        reviews.add(review);
    }

    public void removeReview(int reviewNr){
        reviews.remove(reviewNr);
    }

    public List<String> getReviews(){
        return reviews;
    }

    public void removeAllReviews(){
        reviews.clear();
    }

    public void addCategory(String category){
        categories.add(category);
    }

    public List<String> getCategories(){
        return categories;
    }

    /*public String[] getReviews() {
        return reviews;
    }*/

    /*public void setReviews(String[] reviews) {
        this.reviews = reviews;
    }*/
}
