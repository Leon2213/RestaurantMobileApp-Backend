package com.example.pvt15app;

public class Restaurant {

    private String name;
    private double longitude;
    private double latitude;
    private Boolean isOpen;
    private String address;
    private String photoReference;
    private String placeid;

    public Restaurant() {
    }

    public Restaurant(String name, double longitude, double latitude, Boolean isOpen, String address, String photoReference, String placeid) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.isOpen = isOpen;
        this.address = address;
        this.photoReference = photoReference;
        this.placeid = placeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


    public String getPhotoReference() {
        return photoReference;
    }

    public void setPhotoReference(String photoReference){
        this.photoReference = photoReference;
    }

    public String getPlaceid(){
        return placeid;
    }

    public void setPlaceId(String placeid){
        this.placeid = placeid;
    }



    public Boolean isOpen() {
        if (isOpen == null) {
            return false;
        } else {
            return isOpen;
        }
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Restaurant: " + "\n" +
                "name: " + name + " \n" +
                "longitude: " + longitude + " \n" +
                "latitude: " + latitude + " \n" +
                "isOpen: " + isOpen + " \n" +
                "address: " + address + " \n" +
                "photoRef: " + photoReference + " \n" +
                "placeID: " + placeid;

    }
}
