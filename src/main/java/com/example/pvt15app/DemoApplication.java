package com.example.pvt15app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(DemoApplication.class, args);
		/*RestaurantParser parser = new RestaurantParser();
		parser.startParsing();
		List<Restaurant> lista = parser.getResults();
		lista.forEach(System.out::println);*/
	}

}
