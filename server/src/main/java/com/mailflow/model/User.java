package com.mailflow.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")             //Store this class inside a collection called users
public class User {
    @Id                 //This is the unique identifier (like primary key)
    private String id;                  //filed for the unique identifier
    private String name;                   //field for the user's name
    private String email;                    //field for the user's email

    // Empty constructor (Important for Spring Data MongoDB)
    public User() {}                    //Required by Spring.   Used when reading from database

    //Constructor without ID(used when creating a new user)
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
