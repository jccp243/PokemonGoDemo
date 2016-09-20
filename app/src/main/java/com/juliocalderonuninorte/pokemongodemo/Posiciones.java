package com.juliocalderonuninorte.pokemongodemo;

/**
 * Created by JULIO CALDERON on 20/09/2016.
 */
public class Posiciones {

    public Posiciones(String lat, String lng){
        this.lat = lat;
        this.lng = lng;
    }
    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    private String lat;
    private String lng;

}
