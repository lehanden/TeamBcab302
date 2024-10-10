package com.example.dinnerreserver.model;

public class Restaurant {
    private int id;
    private String name;
    private String address;
    private String description;
    private Float rating;
    private String imageSource;
    private String menuSource;

    public Restaurant(Integer id, String name, String address, String description, Float rating, String imageSource, String menuSource) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.rating = rating;
        this.imageSource = imageSource;
        this.menuSource = menuSource;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getImageSource() { return imageSource; }

    public void setImageSource(String imageSource) { this.imageSource = imageSource; }

    public String getMenuSource() { return menuSource; }

    public void setMenuSource(String menuSource) { this.menuSource = menuSource; }
}
