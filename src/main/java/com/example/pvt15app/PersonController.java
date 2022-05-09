package com.example.pvt15app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
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

    @GetMapping(path="/testNode")
    public String test(@RequestParam Double longitude){
        // Gör anrop till googles API med rätt parametrar
        // ta svaret och parsa det. Skapa object och lägg dom i en lista.
        // Filtrera Listan efter:
        // öppna restauranger.
        // rätt mattyp.
        // returnana listan.
        return String.format("Din kordinat är %s, latitude");
    }

     @RestController
      public class HelloWorldController {

                      @GetMapping("/hello")
              public String hello(@RequestParam(value="name", defaultValue="World") String name){
                  return String.format("Hello %s", name);
              }
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
