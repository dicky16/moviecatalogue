package com.example.moviecatalogue.helper;

public class ProviderEntity {

    private int id;

    private String title;

    private String poster_path;

    private String overview;

    private String release_date;

    //constructor

//    public ProviderEntity(int id, String title, String poster_path, String overview, String release_date) {
//        this.id = id;
//        this.title = title;
//        this.poster_path = poster_path;
//        this.overview = overview;
//        this.release_date = release_date;
//    }

    //getter setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
