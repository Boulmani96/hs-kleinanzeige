package de.hs.da.hskleinanzeigen.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "USER")
@JsonIgnoreProperties({"Created"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(nullable = false)
    private String Email;

    @Column(nullable = false)
    private String Password;

    private String First_name;

    private String Last_name;

    private String Phone;

    private String Location;

    @JsonIgnore
    private LocalDateTime Created;


    public User(String email, String password, String first_name, String last_name, String phone, String location) {
        this.Email = email;
        this.Password = password;
        this.First_name=first_name;
        this.Last_name = last_name;
        this.Phone = phone;
        this.Location = location;
    }

    public User() {

    }

    public Long getID() {
        return ID;
    }

    public void setID(Long id) {
        this.ID = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFirst_name() {
        return First_name;
    }

    public void setFirst_name(String first_name) {
        First_name = first_name;
    }

    public String getLast_name() {
        return Last_name;
    }

    public void setLast_name(String last_name) {
        Last_name = last_name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    @JsonProperty("Created")
    public LocalDateTime getCreated() {
        return Created;
    }
    @JsonProperty("Created")
    public void setCreated(LocalDateTime created) {
        Created = created;
    }

}
