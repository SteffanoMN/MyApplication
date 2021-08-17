package com.example.myapplication;

public class MovieModel {
    private String original_title, release_date, overview, poster_path;

    public MovieModel(String OriginalTitle, String ReleaseDate, String Overview, String PosterPath) {
        this.original_title = OriginalTitle;
        this.release_date = ReleaseDate;
        this.overview = Overview;
        this.poster_path = PosterPath;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}
