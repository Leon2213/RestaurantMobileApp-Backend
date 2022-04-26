package com.example.pvt15app;

import org.springframework.data.repository.CrudRepository;

import com.example.pvt15app.Person;

public interface PersonRepository extends CrudRepository <Person, Integer> {

}
