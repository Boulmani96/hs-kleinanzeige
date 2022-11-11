package de.hs.da.hskleinanzeigen.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CATEGORY")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String Name;

    @ManyToOne
    private Category parent;

    public Category(){

    }

    public Category(String name, Category category){
        this.Name = name;
        this.parent = category;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String name) {
    	this.Name = name;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category category) {
        this.parent = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Category category = (Category) o;
        return id == category.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", category=" + parent +
                '}';
    }
}
