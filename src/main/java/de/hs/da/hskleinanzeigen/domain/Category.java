package de.hs.da.hskleinanzeigen.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CATEGORY")
@JsonIgnoreProperties({"parent"})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    private String name;

    @JsonIgnore
    @ManyToOne
    private Category parent;
/*
    @JsonIgnore
    private int parentId;

    @JsonProperty("parentId")
    public int getParentId() {
        return parentId;
    }

    @JsonProperty("parentId")
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
*/
    public Category(){

    }

    public Category(String name, Category parent){
        this.name = name;
        this.parent = parent;
    }

    public Integer getID() {
        return this.ID;
    }

    public void setID(Integer id) {
        this.ID = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
    	this.name = name;
    }

    @JsonProperty("parent")
    public Category getParent() {
        return parent;
    }

    @JsonProperty("parent")
    public void setParent(Category parent) {
        this.parent = parent;
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
        return ID == category.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + ID +
                ", Name='" + name + '\'' +
                ", parent=" + parent +
                '}';
    }
}
