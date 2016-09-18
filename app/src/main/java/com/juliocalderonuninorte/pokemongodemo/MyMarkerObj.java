package com.juliocalderonuninorte.pokemongodemo;

/**
 * Created by JULIO CALDERON on 18/09/2016.
 */
public class MyMarkerObj {
    private long id;
    private String title;
    private String snippet;
    private String position;

    public MyMarkerObj(){
    }

    public MyMarkerObj(long id, String title, String snippet, String position){
        this.id = id;
        this.title = title;
        this.snippet = snippet;
        this.position = position;
    }

    public MyMarkerObj(String title, String snippet, String position){
        this.title = title;
        this.snippet = snippet;
        this.position = position;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
