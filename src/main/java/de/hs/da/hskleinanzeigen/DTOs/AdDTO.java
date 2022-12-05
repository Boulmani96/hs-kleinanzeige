package de.hs.da.hskleinanzeigen.DTOs;

import de.hs.da.hskleinanzeigen.domain.Type;

public class AdDTO {
    private int id;
    private Type type;
    private CategoryDTO categoryDTO;
    private UserDTO userDTO;
    private String title;
    private String description;
    private int price;
    private String location;

    public AdDTO(int id, Type type, CategoryDTO categoryDTO, UserDTO userDTO, String title, String description, int price, String location) {
        this.id = id;
        this.type = type;
        this.categoryDTO = categoryDTO;
        this.userDTO = userDTO;
        this.title = title;
        this.description = description;
        this.price = price;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public CategoryDTO getCategoryDTO() {
        return categoryDTO;
    }

    public void setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
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
