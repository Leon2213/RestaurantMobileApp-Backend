package com.example.pvt15app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping()
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String role){
        Person n = new Person();
        n.setName(name);
        n.setRole(role);
        personRepository.save(n);
        return "Saved new user " + name;
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Person> getAll(){
        return personRepository.findAll();
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
