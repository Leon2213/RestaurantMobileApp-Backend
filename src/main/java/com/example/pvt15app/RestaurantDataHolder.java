package com.example.pvt15app;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    private String[] reviews = new String[20];

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

    public String[] getReviews() {
        return reviews;
    }

    public void setReviews(String[] reviews) {
        this.reviews = reviews;
    }
}
