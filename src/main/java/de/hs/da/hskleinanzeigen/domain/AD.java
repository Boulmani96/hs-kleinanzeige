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
    private Integer ID;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type Type;

    private Integer Price;

    private String Location;

    @JsonIgnore
    private LocalDateTime Created;

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
    private String Title;

    @Column(nullable = false)
    private String Description;

    public AD() {

    }

    public AD(Type type, Category category,User user, String title, String description, Integer price, String location) {
        this.Type = type;
        this.category = category;
        this.user = user;
        this.Title = title;
        this.Description = description;
        this.Price = price;
        this.Location = location;
    }

    public Integer getID() {
        return this.ID;
    }

    public void setID(Integer id) {
        this.ID = id;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Type getType() {
        return this.Type;
    }

    public void setType(Type type) {
    	this.Type = type;
    }

    public String getTitle() {
        return this.Title;
    }

    public void setTitle(String title) {
    	this.Title = title;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String description) {
    	this.Description = description;
    }

    public Integer getPrice() {
        return this.Price;
    }

    public void setPrice(Integer price) {
    	this.Price = price;
    }

    public String getLocation() {
        return this.Location;
    }

    public void setLocation(String location) {
    	this.Location = location;
    }

    @JsonProperty("Created")
    public LocalDateTime getCreated() {
        return this.Created;
    }

    @JsonProperty("Created")
    public void setCreated(LocalDateTime created) {
    	this.Created = created;
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
                ", Type=" + Type +
                ", category=" + category +
                ", Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", Price=" + Price +
                ", Location='" + Location + '\'' +
                ", Created=" + Created +
                '}';
    }
}
