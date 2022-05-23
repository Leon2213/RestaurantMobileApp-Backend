package com.example.pvt15app;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Filter {
    RestaurantDataHolderRepository repository;
    List<Restaurant> googleResults;
    List<String> foodCriteria;
    List<Restaurant> results = new ArrayList<>();

    public Filter(List<String> foodCriteriaList, List<Restaurant> googleRestaurantResultList, RestaurantDataHolderRepository dataRepository) {
        this.googleResults = googleRestaurantResultList;
        this.foodCriteria = foodCriteriaList;
        this.repository = dataRepository;
        filter();
    }

    public void filter(){
        for (Restaurant googleRestaurant : googleResults) {
               String id = googleRestaurant.getRestaurantId();
            if (id != null) {
                System.out.println("detta är id");
                System.out.println(id);
                Optional<RestaurantDataHolder> optionalRestaurantFromDatabase = repository.findById(id);
                System.out.println("testprinta optional objekt: ");
                System.out.println(optionalRestaurantFromDatabase);
                boolean restaurantExistsInDatabase = optionalRestaurantFromDatabase.isPresent();

                if(restaurantExistsInDatabase){
                    System.out.println("detta är inne i om objektet fanns");
                    filterGoogleAgainstDatabase(googleRestaurant, optionalRestaurantFromDatabase);
                }
            }
        }
    }

    private void filterGoogleAgainstDatabase(Restaurant restaurant, Optional<RestaurantDataHolder> restaurantDataHolderOptional) {
        System.out.println("restaurant print: " + restaurant);
        System.out.println("Optional Dataholder print: " + restaurantDataHolderOptional);

        List<String> availableFood = getRestaurantFoodTypes(restaurantDataHolderOptional);
        System.out.println("--start Availablefood:");
        System.out.println(availableFood);
        System.out.println("--slut AvailableFood");
        System.out.println("--start foodCriteria");
        System.out.println(foodCriteria);
        System.out.println("--end foodCriteria");

        int counter1 = 0;
        int counter2 = 0;
        for (String requestedFoodType : foodCriteria) {
            counter1++;
            System.out.println(counter1);
            System.out.println("Användaren requestade: ");
            System.out.println(requestedFoodType);
            for (String availableFoodType : availableFood) {
                counter2++;
                System.out.println(counter2);
                System.out.println("den tillgängliga maten är");
                System.out.println(availableFoodType);
                if(availableFoodType.contains(requestedFoodType)) {
                    System.out.println("detta är inne contains true");
                    if(!results.contains(restaurant)) {
                        System.out.println("detta är inne i om resultatet inte har denna restaurangen");
                        results.add(restaurant);
                        restaurant.addFoodtypes(availableFood);
                        System.out.println("--start reviewsdetta är reviews");
                        System.out.println(restaurantDataHolderOptional.get().getReviews());
                        System.out.println("--end reviews");
                        restaurant.setReviews(restaurantDataHolderOptional.get().getReviews());
                    }
                };
            }
        }
        System.out.println("--Start resultatlistan:");
        System.out.println(results);
        System.out.println("--End resultatlistan:");
    }

    private List<String> getRestaurantFoodTypes(Optional<RestaurantDataHolder> restaurantDataHolderOptional) {
        RestaurantDataHolder restaurantDataHolder = restaurantDataHolderOptional.get();
        List<String> availableFood = restaurantDataHolder.getCategories();
        return availableFood;
    }

    public List<Restaurant> getResults(){
        return results;
    }

}

