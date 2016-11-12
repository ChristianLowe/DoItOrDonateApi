package com.kalieki.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

/**
 * Created by kalieki on 9/24/16.
 */

@Entity
public class User {


    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column()
    private String email;

   /*
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy="owner", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<House> houses;
   */
    public User() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username != null ? username.toLowerCase() : null;
    }

    public void setUsername(String username) {
        this.username = username != null ? username.toLowerCase() : null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
