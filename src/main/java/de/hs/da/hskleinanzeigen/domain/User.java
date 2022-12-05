package de.hs.da.hskleinanzeigen.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "USER")
@JsonIgnoreProperties({"Created", "password"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String email;

    @JsonIgnore
    //@Size(min=5, message="Name should have at least 5 characters")
    private String password;

    @Column(name = "first_Name")
    private String firstName;

    @Column(name = "last_Name")
    private String lastName;

    private String phone;

    private String location;

    @JsonIgnore
    private LocalDateTime created;

    public User(String email, String password, String first_name, String last_name, String phone, String location) {
        this.email = email;
        this.password = password;
        this.firstName = first_name;
        this.lastName = last_name;
        this.phone = phone;
        this.location = location;
    }

    public User() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @JsonProperty("Created")
    public LocalDateTime getCreated() {
        return created;
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
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + id +
                ", Email='" + email + '\'' +
                ", Password='" + password + '\'' +
                ", First_name='" + firstName + '\'' +
                ", Last_name='" + lastName + '\'' +
                ", Phone='" + phone + '\'' +
                ", Location='" + location + '\'' +
                ", Created=" + created +
                '}';
    }
}
