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

    private Boolean hamburger;

    private Boolean korv;

    private Boolean pizza;

    private Boolean kebab;

    private Boolean snacks;

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

    public Boolean getHamburger() {
        return hamburger;
    }

    public void setHamburger(Boolean hamburger) {
        this.hamburger = hamburger;
    }

    public Boolean getKorv() {
        return korv;
    }

    public void setKorv(Boolean korv) {
        this.korv = korv;
    }

    public Boolean getPizza() {
        return pizza;
    }

    public void setPizza(Boolean pizza) {
        this.pizza = pizza;
    }

    public Boolean getKebab() {
        return kebab;
    }

    public void setKebab(Boolean kebab) {
        this.kebab = kebab;
    }

    public Boolean getSnacks() {
        return snacks;
    }

    public void setSnacks(Boolean snacks) {
        this.snacks = snacks;
    }

    public void addReview(String review) {
        reviews.add(review);
    }

    public void removeReview(int reviewNr){
        reviews.remove(reviewNr);
    }

    public List<String> getReview(){
        return reviews;
    }

    public void removeAllReviews(){
        reviews.clear();
    }

    /*public String[] getReviews() {
        return reviews;
    }*/

    /*public void setReviews(String[] reviews) {
        this.reviews = reviews;
    }*/
}
