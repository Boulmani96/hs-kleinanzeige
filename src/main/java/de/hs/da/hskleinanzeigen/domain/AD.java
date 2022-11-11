package de.hs.da.hskleinanzeigen.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "AD")
public class AD {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type Type;

    @ManyToOne
    private Category category;

    @Column(nullable = false)
    private String Title;

    @Column(nullable = false)
    private String Description;

    private int Price;

    private String Location;

    private Timestamp Created;

    public AD() {

    }

    public AD(Type type, Category category, String title, String description, int price, String location, Timestamp created) {
        this.Type = type;
        this.category = category;
        this.Title = title;
        this.Description = description;
        this.Price = price;
        this.Location = location;
        this.Created = created;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPrice() {
        return this.Price;
    }

    public void setPrice(int price) {
    	this.Price = price;
    }

    public String getLocation() {
        return this.Location;
    }

    public void setLocation(String location) {
    	this.Location = location;
    }

    public Timestamp getCreated() {
        return this.Created;
    }

    public void setCreated(Timestamp created) {
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
        return id == ad.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AD{" +
                "id=" + id +
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
