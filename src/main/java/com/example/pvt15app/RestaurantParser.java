package com.example.pvt15app;

import com.example.pvt15app.Restaurant;
import com.google.gson.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RestaurantParser {
    static List<Restaurant> restaurantList = new ArrayList<>();
        URL url;
        String urlString;
        InputStreamReader reader;
        JsonParser parser;
        JsonObject jsonObject;
        JsonArray jsonArray;


    public RestaurantParser(double latitude, double longitude) throws IOException {
        String urlString = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?keyword=restaurant&location="+latitude+","+longitude+"&radius=500&type=restaurant&key=AIzaSyBmfU0WjQP9e5XMV09t1-UP-M0892jPmkA";
        this.urlString = urlString;
        this.url = new URL(urlString);
        this.reader = new InputStreamReader(url.openStream());
        this.parser = new JsonParser();
        this.jsonObject = parser.parse(reader).getAsJsonObject();
        this.jsonArray = jsonObject.get("results").getAsJsonArray();
    }

    public RestaurantParser() throws IOException {
        String urlString = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?keyword=restaurant&location=59.342069,18.095902&radius=500&type=restaurant&key=AIzaSyBmfU0WjQP9e5XMV09t1-UP-M0892jPmkA";
        this.urlString = urlString;
        this.url = new URL(urlString);
        this.reader = new InputStreamReader(url.openStream());
        this.parser = new JsonParser();
        this.jsonObject = parser.parse(reader).getAsJsonObject();
        this.jsonArray = jsonObject.get("results").getAsJsonArray();
    }

    public void startParsing(){
        restaurantList = new ArrayList<>();
        parseJsonArray(jsonArray);
    }

    private static void parseJsonArray(JsonArray jsonArray) {
        for (JsonElement jsonRestaurantObject : jsonArray) {
            parseJSONobjectAndCreateRestaurantObject(jsonRestaurantObject);
        }
    }

  /*  private static void PrintRestaurants() {
        for (Restaurant r : restaurantList) {
            System.out.println(r + "\n");
        }
    }*/

    private static void parseJSONobjectAndCreateRestaurantObject(JsonElement jsonRestaurant) {
        Restaurant restaurant = new Restaurant();

        parseAndSetGeoCoordinates(jsonRestaurant, restaurant);
        parseAndSetOpenStatus(jsonRestaurant, restaurant);
        parseRestaurantName(jsonRestaurant, restaurant);
        parseStreetAddress(jsonRestaurant, restaurant);

        if(restaurant.isOpen())
        restaurantList.add(restaurant);
    }

    private static void parseStreetAddress(JsonElement jsonRestaurant, Restaurant restaurant) {
        String streetAddress = jsonRestaurant.getAsJsonObject().get("vicinity").getAsString();

        restaurant.setAddress(streetAddress);
    }

    private static void parseRestaurantName(JsonElement jsonRestaurant, Restaurant restaurant) {
        String restaurantName = jsonRestaurant.getAsJsonObject().get("name").getAsString();

        restaurant.setName(restaurantName);
    }

    private static void parseAndSetOpenStatus(JsonElement jsonRestaurant, Restaurant restaurant) {
        Optional<JsonElement> optionalNull = Optional.ofNullable(jsonRestaurant);
        optionalNull.map(a -> a.getAsJsonObject())
                .map(b -> b.get("opening_hours"))
                .map(c -> c.getAsJsonObject())
                .map(d -> d.get("open_now"))
                .map(e -> e.getAsBoolean())
                .ifPresent(f -> restaurant.setOpen(f));

    }

    private static void parseAndSetGeoCoordinates(JsonElement jsonRestaurant, Restaurant restaurant) {
        // JsonArray geometryJSONArray = jsonRestaurant.getAsJsonObject().get("geometry").getAsJsonArray();

        JsonObject obj = jsonRestaurant.getAsJsonObject();
        JsonElement geometryElement = obj.get("geometry");
        JsonObject geometryObject = geometryElement.getAsJsonObject();
        JsonElement locationElement = geometryObject.get("location");
        JsonObject locationObject = locationElement.getAsJsonObject();
        double latitude = locationObject.get("lat").getAsDouble();
        double longitude = locationObject.get("lng").getAsDouble();

        restaurant.setLatitude(latitude);
        restaurant.setLongitude(longitude);
    }

    public List<Restaurant> getResults() {
        return restaurantList;
    }

    public String getUrl(){
        return urlString;
    }

}
