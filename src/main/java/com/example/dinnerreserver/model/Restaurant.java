package com.example.dinnerreserver.model;

/**
 * A simple class for the restaurant containing the same parameters as the restaurants database
 */
public class Restaurant {
    private int id;
    private String name;
    private String address;
    private String description;
    private Float rating;
    private String imageSource;
    private String menuSource;

    /**
     * Constructor methods for each of the restaurant parameters
     * @param id The restaurant id that servers as a Primary key in the database
     * @param name The name of the restaurant
     * @param address The restaurant's street address
     * @param description A short description of the restaurant
     * @param rating The public rating of the restaurant
     * @param imageSource An image of the restaurant
     * @param menuSource The link to the restaurant's online menu
     */
    public Restaurant(Integer id, String name, String address, String description, Float rating, String imageSource, String menuSource) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.rating = rating;
        this.imageSource = imageSource;
        this.menuSource = menuSource;
    }

    /**
     * Gets the current restaurant id
     * @return The id of the current restaurant
     */
    public int getId() {
        return id;
    }

    /**
     * Updates the current restaurant to use the given restaurant id
     * @param id The new restaurant id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the current restaurant's name
     * @return The name of the restaurant
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the current restaurant to use the given name
     * @param name The new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the current restaurant's address
     * @return The restaurant's street address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Updates the current restaurant to use the given address
     * @param address The new address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the current restaurant's description
     * @return A short description of the restaurant
     */
    public String getDescription() {
        return description;
    }

    /**
     * Updates the current restaurant to use the given description
     * @param description The new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the current restaurant's online rating
     * @return The public rating of the restaurant
     */
    public Float getRating() {
        return rating;
    }

    /**
     * Updates the current restaurant to use the given rating
     * @param rating The new restaurant rating
     */
    public void setRating(Float rating) {
        this.rating = rating;
    }

    /**
     * Gets the current restaurant's image
     * @return The source for the image of the restaurant
     */
    public String getImageSource() { return imageSource; }

    /**
     * Updates the current restaurant to use the given image source
     * @param imageSource The new image source
     */
    public void setImageSource(String imageSource) { this.imageSource = imageSource; }

    /**
     * Gets the current restaurant's online menu link
     * @return The link to the restaurant's online meny
     */
    public String getMenuSource() { return menuSource; }

    /**
     * Updates the current restaurant to use the given menu link
     * @param menuSource The new menu source
     */
    public void setMenuSource(String menuSource) { this.menuSource = menuSource; }
}
