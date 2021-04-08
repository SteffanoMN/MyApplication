package com.example.myapplication;

public class Microsoft {
    private String name;
    private String version;
    int picture;
    private String description;

    public Microsoft(String name, String version, String description, int picture) {
        this.name = name;
        this.version = version;
        this.description = description;
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProduct() {
        return name;
    }

    public void setProduct(String name) {
        this.name = name;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getVers() {
        return version;
    }

    public void setVers(String version) {
        this.version = version;
    }
}
