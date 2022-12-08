package de.hs.da.hskleinanzeigen.DTOs;

import javax.validation.constraints.NotNull;

public class CreationAdDTO {
    @NotNull(message = "Type cannot be null")
    private String type;

    @NotNull(message = "Category cannot be null")
    private CategoryDTO category;

    @NotNull(message = "User cannot be null")
    private UserDTO user;

    @NotNull(message = "Title cannot be null")
    private String title;

    @NotNull(message = "Description cannot be null")
    private String description;

    private int price;

    private String location;

    public CreationAdDTO(){

    }

    public CreationAdDTO(String type, CategoryDTO category, UserDTO user, String title, String description, int price, String location) {
        this.type = type;
        this.category = category;
        this.user = user;
        this.title = title;
        this.description = description;
        this.price = price;
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
