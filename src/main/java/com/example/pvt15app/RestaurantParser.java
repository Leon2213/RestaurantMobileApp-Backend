package com.example.pvt15app;

import com.google.gson.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RestaurantParser {
    static List<Restaurant> restaurantList = new ArrayList<>();
    List<String> differentRestaurantTypesUrl = new ArrayList<>();


    public RestaurantParser(double latitude, double longitude) throws IOException {
        restaurantList.clear();
        System.out.println(" inuti konstruktorn ");

        String urlStringRestaurant = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?keyword=restaurant&location="+latitude+","+longitude+"&radius=1000&type=restaurant&key=AIzaSyCTAeAanf7zvaO-XuF1--LeRGunC1w6Puw";
        String urlStringFastFood = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?keyword=fastfood&location="+latitude+","+longitude+"&radius=1000&type=restaurant&key=AIzaSyCTAeAanf7zvaO-XuF1--LeRGunC1w6Puw";
        String urlStringGrill = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?keyword=grill&location="+latitude+","+longitude+"&radius=1000&type=restaurant&key=AIzaSyCTAeAanf7zvaO-XuF1--LeRGunC1w6Puw";
        String urlStringKebab = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?keyword=kebab&location="+latitude+","+longitude+"&radius=1000&key=AIzaSyCTAeAanf7zvaO-XuF1--LeRGunC1w6Puw";
        String urlStringConvenienceStore = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?keyword=conveniencestore&location="+latitude+","+longitude+"&radius=1000&key=AIzaSyCTAeAanf7zvaO-XuF1--LeRGunC1w6Puw";
        differentRestaurantTypesUrl.add(urlStringRestaurant);
        differentRestaurantTypesUrl.add(urlStringFastFood);
        differentRestaurantTypesUrl.add(urlStringGrill);
        differentRestaurantTypesUrl.add(urlStringKebab);
        differentRestaurantTypesUrl.add(urlStringConvenienceStore);

        System.out.println("--start url-lista");
        System.out.println(differentRestaurantTypesUrl);
        System.out.println("--end url-lista");

        parseRestaurants();


       /* this.urlStringRestaurant = urlStringRestaurant;
        this.url = new URL(urlStringRestaurant);
        this.reader = new InputStreamReader(url.openStream());
        this.parser = new JsonParser();
        this.jsonObject = parser.parse(reader).getAsJsonObject();
        this.jsonArray = jsonObject.get("results").getAsJsonArray();*/
    }


    private void parseRestaurants() throws IOException {
        for (String urlString : differentRestaurantTypesUrl) {
            URL url = new URL(urlString);
            Reader reader = new InputStreamReader(url.openStream());
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(reader).getAsJsonObject();
            JsonArray jsonArray = jsonObject.get("results").getAsJsonArray();
            System.out.println("--start JsonArray I parseRestaurants()");
            System.out.println(jsonArray);
            System.out.println("--end JsonArray I parseRestaurants()");
            startParsing(jsonArray);

        }
    }


    /*public RestaurantParser() throws IOException {
        String urlString = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?keyword=restaurant&location=59.342069,18.095902&radius=500&type=restaurant&key=AIzaSyBmfU0WjQP9e5XMV09t1-UP-M0892jPmkA";
        this.urlStringRestaurant = urlString;
        this.url = new URL(urlString);
        this.reader = new InputStreamReader(url.openStream());
        this.parser = new JsonParser();
        this.jsonObject = parser.parse(reader).getAsJsonObject();
        this.jsonArray = jsonObject.get("results").getAsJsonArray();
    }*/

    public void startParsing(JsonArray jsonArray){
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
    //

    private static void parseJSONobjectAndCreateRestaurantObject(JsonElement jsonRestaurant) {
        Restaurant restaurant = new Restaurant();
        parseAndSetGeoCoordinates(jsonRestaurant, restaurant);
        parseAndSetOpenStatus(jsonRestaurant, restaurant);
        parseRestaurantName(jsonRestaurant, restaurant);
        parseStreetAddress(jsonRestaurant, restaurant);
        parsePhotoReference(jsonRestaurant, restaurant);
        parsePlaceId(jsonRestaurant, restaurant);
        parseRating(jsonRestaurant, restaurant);



            if(restaurant.isOpen() && !restaurantList.contains(restaurant)) {
                restaurantList.add(restaurant);
            }

        /*if(restaurant.isOpen() && !restaurantList.contains(restaurant)) {
            restaurantList.add(restaurant);
        }*/
    }


    private static void parsePhotoReference(JsonElement jsonRestaurant, Restaurant restaurant) {

        JsonObject obj = jsonRestaurant.getAsJsonObject();
        System.out.println("printar obj");
        System.out.println(obj);
        JsonElement photosElement = obj.get("photos");
        if(photosElement == null){
            return;
        }
        System.out.println("printar photosElement");
        System.out.println(photosElement);
        JsonArray photoArray = photosElement.getAsJsonArray();
        System.out.println("printar photoArray");
        System.out.println(photoArray);
        JsonElement element1 = photoArray.get(0);
        System.out.println("printar element 1");
        System.out.println(element1);
        JsonObject photoObj = element1.getAsJsonObject();
        System.out.println("printar photoObj");
        System.out.println(photoObj);
        String photoRefString = photoObj.get("photo_reference").getAsString();
        System.out.println("printar photoRefString");
        System.out.println(photoRefString);

        //JsonObject photoObject = photosElement.getAsJsonObject();*/
        // System.out.println(photoRefString);
        /*JsonObject photosObject = photosElement.getAsJsonObject();
        JsonElement photoElement0 = photosObject.get("0");
        JsonObject photoObject0 = photoElement0.getAsJsonObject();
        String photoRefString = photoObject0.get("photo_reference").getAsString();*/

        restaurant.setPhotoReference(photoRefString);

    }

    private static void parsePlaceId(JsonElement jsonRestaurant, Restaurant restaurant) {
        String placeId = jsonRestaurant.getAsJsonObject().get("place_id").getAsString();
        restaurant.setRestaurantId(placeId);
    }



    private static void parseRating(JsonElement jsonRestaurant, Restaurant restaurant) {
        String rating = jsonRestaurant.getAsJsonObject().get("rating").getAsString();
       // System.out.println(placeId);
        restaurant.setRating(rating);
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



    public List<Restaurant> getResults() {
        return restaurantList;
    }


}
