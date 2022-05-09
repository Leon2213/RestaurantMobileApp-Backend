package com.example.pvt15app;

import com.example.pvt15app.Restaurant;
import com.google.gson.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RestaurantParser {
    static List<Restaurant> restaurantList = new ArrayList<>();
        private URL url = new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/json?keyword=restaurant&location=59.412983,18.034189&radius=250&type=restaurant&key=AIzaSyBmfU0WjQP9e5XMV09t1-UP-M0892jPmkA");
        private InputStreamReader reader = new InputStreamReader(url.openStream());
        private JsonParser parser = new JsonParser();
        private JsonObject jsonObject = parser.parse(reader).getAsJsonObject();
        private JsonArray jsonArray = jsonObject.get("results").getAsJsonArray();


    public RestaurantParser() throws IOException {
    }

    public void startParsing(){
        parseJsonArray(jsonArray);
    }

    private static void parseJsonArray(JsonArray jsonArray) {
        for (JsonElement jsonRestaurantObject : jsonArray) {
            parseJSONobjectAndCreateRestaurantObject(jsonRestaurantObject);
        }
    }

    private static void PrintRestaurants() {
        for (Restaurant r : restaurantList) {
            System.out.println(r + "\n");
        }
    }

    private static void parseJSONobjectAndCreateRestaurantObject(JsonElement jsonRestaurant) {
        Restaurant restaurant = new Restaurant();

        parseAndSetGeoCoordinates(jsonRestaurant, restaurant);
        parseAndSetOpenStatus(jsonRestaurant, restaurant);
        parseRestaurantName(jsonRestaurant, restaurant);
        parseStreetAddress(jsonRestaurant, restaurant);

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
        JsonElement opening_hoursElement = jsonRestaurant.getAsJsonObject().get("opening_hours");
        JsonObject opening_hoursObject = opening_hoursElement.getAsJsonObject();
        boolean openStatus = opening_hoursObject.get("open_now").getAsBoolean();


        restaurant.setOpen(openStatus);
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

}
