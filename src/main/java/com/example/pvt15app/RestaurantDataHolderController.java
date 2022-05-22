package com.example.pvt15app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public @ResponseBody List<Restaurant> returnRestaurants3 (@RequestParam(value="latitude") Double latitude, @RequestParam (value="longitude") Double longitude, @RequestParam(value="type") String type){
        System.out.println("latiude: " +latitude+ "\n" + "longitude: " +longitude+ "\n" + "type: " + type);
        //double testLat = 59.342069;
        // double testLong = 18.095902;
        try{
            RestaurantParser parser = new RestaurantParser(latitude, longitude);
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

/*
    @PostMapping(path="/add")
    public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String role){
        Person n = new Person();
        n.setName(name);
        n.setRole(role);
        dataRepository.save(n);
        return "Saved new user " + name;
    } */

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

    @PostMapping(path="/addReview")
    public @ResponseBody String addNewReviewData (@RequestParam String id, @RequestParam String review){
        dataRepository.findById(id).ifPresent(
                restaurant -> restaurant.addReview(review));
        dataRepository.findById(id).ifPresent(
                restaurant -> dataRepository.save(restaurant));
                return "the new review has been added";
    }



    /*
    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deleteById(@PathVariable(value= "id") int id) {
        Optional<Person> p = dataRepository.findById(id);
        if(p.isPresent()){
            dataRepository.deleteById(id);
            return "successfully deleted person with id=" + id;
        } else {
            return "could not find person with id=" + id;
        }


    } */
}
