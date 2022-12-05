package de.hs.da.hskleinanzeigen.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "AD")
@JsonIgnoreProperties({"Created"})
public class AD {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    private int price;

    private String location;

    @JsonIgnore
    private LocalDateTime created;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    public AD() {

    }

    public AD(Type type, Category category,User user, String title, String description, int price, String location) {
        this.type = type;
        this.category = category;
        this.user = user;
        this.title = title;
        this.description = description;
        this.price = price;
        this.location = location;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
    	this.type = type;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
    	this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
    	this.description = description;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
    	this.price = price;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
    	this.location = location;
    }

    @JsonProperty("Created")
    public LocalDateTime getCreated() {
        return this.created;
    }

    @JsonProperty("Created")
    public void setCreated(LocalDateTime created) {
    	this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AD ad = (AD) o;
        return ID == ad.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    @Override
    public String toString() {
        return "AD{" +
                "id=" + ID +
                ", Type=" + type +
                ", category=" + category +
                ", Title='" + title + '\'' +
                ", Description='" + description + '\'' +
                ", Price=" + price +
                ", Location='" + location + '\'' +
                ", Created=" + created +
                '}';
    }
}
