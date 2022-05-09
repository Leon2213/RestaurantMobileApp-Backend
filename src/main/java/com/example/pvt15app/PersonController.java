package com.example.pvt15app;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="/app")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Person> getAll(){
        return personRepository.findAll();
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

    @GetMapping(path="/findnearbyrestaurants2")
    public @ResponseBody String returnRestaurants2 (@RequestParam(value="latitude") Double latitude, @RequestParam (value="longitude") Double longitude, @RequestParam(value="type") String type) throws IOException {
        RestaurantParser parser = new RestaurantParser();
        parser.startParsing();
        List<Restaurant> restaurants = parser.getResults();

        // Gör anrop till googles API med rätt parametrar
        // ta svaret och parsa det. Skapa object och lägg dom i en lista.
        // Filtrera Listan efter:
        // öppna restauranger.
        // rätt mattyp.
        // returnana listan.

        String result = new Gson().toJson(restaurants);

        return result;
    }


    @PostMapping(path="/add")
    public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String role){
        Person n = new Person();
        n.setName(name);
        n.setRole(role);
        personRepository.save(n);
        return "Saved new user " + name;
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deleteById(@PathVariable(value= "id") int id) {
        Optional<Person> p = personRepository.findById(id);
        if(p.isPresent()){
            personRepository.deleteById(id);
            return "successfully deleted person with id=" + id;
        } else {
            return "could not find person with id=" + id;
        }


    }
}
