package com.example.pvt15app;

import java.util.List;
import java.util.Map;

public class Filter {
    RestaurantDataHolderRepository repository;
    Map<String, RestaurantDataHolder> idToDatabaseMap;
    List<Restaurant> googleResults;
    List<String> foodCriteria;

    public Filter(List<String> foodCriteriaList, List<Restaurant> googleRestaurantResultList) {
        this.googleResults = googleRestaurantResultList;
        this.foodCriteria = foodCriteriaList;

    }

    public void method(){
        for (Restaurant restaurant : googleResults) {
                restaurant.getRestaurantId();
        }
    }

}

