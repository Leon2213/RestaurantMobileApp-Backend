package com.example.pvt15app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Controller
@RequestMapping(path="/app")
public class RestaurantDataHolderController {
    List<Restaurant> rList = List.of(new Restaurant("empty", 1.0,2.0,true,"emptyStreet"));

    @Autowired
    private RestaurantDataHolderRepository dataRepository;

    public RestaurantDataHolderController() throws IOException {
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<RestaurantDataHolder> getAll(){
        return dataRepository.findAll();
    }

  /*  @GetMapping(path="/findNearbyRestaurants")
    public @ResponseBody Iterable<Restaurant> getAll(@RequestParam Double l, @RequestParam Double lat, @RequestParam String restaurantType){
        // Gör anrop till googles API med rätt parametrar
        // ta svaret och parsa det. Skapa object och lägg dom i en lista.
        // Filtrera Listan efter:
        // öppna restauranger.
        // rätt mattyp.
        // returnana listan.
        return null;
    }*/

    @GetMapping(path="/findnearbyrestaurants")
    public @ResponseBody String returnRestaurants (@RequestParam(value="latitude") Double latitude, @RequestParam (value="longitude") Double longitude, @RequestParam(value="type") String type){
        // Gör anrop till googles API med rätt parametrar
        // ta svaret och parsa det. Skapa object och lägg dom i en lista.
        // Filtrera Listan efter:
        // öppna restauranger.
        // rätt mattyp.
        // returnana listan.
        return String.format("Din koordinat är %s %s %s", latitude, longitude, type);
    }


    @GetMapping(path="/erik2")
    public @ResponseBody List<Restaurant> returnRestaurants2 (){
       // double testLat = 59.342069;
       // double testLong = 18.095902;
        try{
            RestaurantParser parser = new RestaurantParser();
            parser.startParsing();
            List<Restaurant> googleRestaurantResultList = parser.getResults();
            // ta strängen type och hämta ut de kategorier användaren vill ha.
            // matcha googleRestaurantResultList med vår egen databas som har info
            // om vilka restauranger som matchar användarens önskemål.
            // returnera sedan de restauranger som passar användarens önskemål.
            System.out.println(parser.getUrl());
            return googleRestaurantResultList;
        } catch (IOException exception) {
            return rList;
        }
    }
    //

    @GetMapping(path="/find")
    public @ResponseBody List<Restaurant> returnRestaurants3 (@RequestParam(value="latitude") Double latitude, @RequestParam (value="longitude") Double longitude, @RequestParam(value="type") String foodtypes){
        List<String> foodCriteriaList = Arrays.asList(foodtypes.split(","));
        System.out.println("latiude: " +latitude+ "\n" + "longitude: " +longitude+ "\n" + "type: ");
        //double testLat = 59.342069;
        // double testLong = 18.095902;
        try{
            RestaurantParser parser = new RestaurantParser(latitude, longitude);
            parser.startParsing();
            List<Restaurant> googleRestaurantResultList = parser.getResults();
            //Filter filter = new Filter(foodCriteriaList, googleRestaurantResultList);

            //filterResultsOnRequestedFoodtype(googleRestaurantResultList, hamburger, korv, pizza, kebab, snacks);
            // ta strängen type och hämta ut de kategorier användaren vill ha.
            // matcha googleRestaurantResultList med vår egen databas som har info
            // om vilka restauranger som matchar användarens önskemål.
            // returnera sedan de restauranger som passar användarens önskemål.
            System.out.println(parser.getUrl());
            return googleRestaurantResultList;
        } catch (IOException exception) {
            return rList;
        }
    }






    @GetMapping(path="/erik")
    public @ResponseBody List<Restaurant> returnErik (@RequestParam(value="latitude") Double latitude, @RequestParam (value="longitude") Double longitude, @RequestParam(value="type") String type) {
        try{
            RestaurantParser parser = new RestaurantParser(latitude, longitude);
            parser.startParsing();
            List<Restaurant> googleRestaurantResultList = parser.getResults();
            // ta strängen type och hämta ut de kategorier användaren vill ha.
            // matcha googleRestaurantResultList med vår egen databas som har info
            // om vilka restauranger som matchar användarens önskemål.
            // returnera sedan de restauranger som passar användarens önskemål.
            return googleRestaurantResultList;
        } catch (IOException exception) {
            return rList;
        }


        // Gör anrop till googles API med rätt parametrar
        // ta svaret och parsa det. Skapa object och lägg dom i en lista.
        // Filtrera Listan efter:
        // öppna restauranger.
        // rätt mattyp.
        // returnana listan.

        //String result = new Gson().toJson(restaurants);

    }



    @PostMapping(path="/addRestaurant")
    public @ResponseBody String addNewRestaurantData (@RequestParam String id, @RequestParam String name, @RequestParam Boolean hamburger, @RequestParam Boolean korv, @RequestParam Boolean pizza, @RequestParam Boolean kebab, @RequestParam Boolean snacks){
        RestaurantDataHolder newRestaurant = new RestaurantDataHolder();

        newRestaurant.setPlaceid(id);
        newRestaurant.setRestaurantName(name);
        newRestaurant.setHamburger(hamburger);
        newRestaurant.setKorv(korv);
        newRestaurant.setPizza(pizza);
        newRestaurant.setKebab(kebab);
        newRestaurant.setSnacks(snacks);
        //newRestaurant.addReview("wow va gott det var");
        dataRepository.save(newRestaurant);
        return "Saved new restaurant " + name +" with id: " + id;
    }

    @DeleteMapping(path="/deleteRestaurant")
    public @ResponseBody String deleteRestaurantData (@RequestParam String id){
        Optional<RestaurantDataHolder> restaurantDataHolder = dataRepository.findById(id);
        if(restaurantDataHolder.isPresent()){
            dataRepository.delete(restaurantDataHolder.get());
        } else {
            return "restaurangen fanns inte. Kontrollera ID och försök igen.";
        }

        return "Tog bort restaurangenen med id: " + id;
    }

    @PostMapping(path="/addReview")
    public @ResponseBody String addNewReviewData (@RequestParam String id, @RequestParam String review){
        dataRepository.findById(id).ifPresent(
                restaurant -> restaurant.addReview(review));
        dataRepository.findById(id).ifPresent(
                restaurant -> dataRepository.save(restaurant));
                return "the new review has been added";
    }


   /* // Tycks inte funka att skicka 2 parameterar till delete.
    @DeleteMapping(path="/deleteReview")
    public @ResponseBody String deleteReview (@RequestParam String reviewId, @RequestParam String id){
        int reviewIndexNr = Integer.valueOf(reviewId);
        String reviewString;
        Optional<RestaurantDataHolder> restaurantDataHolder = dataRepository.findById(id);
        if(restaurantDataHolder.isPresent()){
            restaurantDataHolder.get().removeAllReviews();
            reviewString = restaurantDataHolder.get().getReview().get(reviewIndexNr);
            restaurantDataHolder.get().removeReview(reviewIndexNr);
        } else {
            return "restaurangen eller reviewnr fanns inte. Kontrollera ID och reviewNr och försök igen.";
        }
        dataRepository.save(restaurantDataHolder.get());
        return "Tog bort reviewen: " + reviewString + " från restaurangen med ID " + id;
    }*/






}
