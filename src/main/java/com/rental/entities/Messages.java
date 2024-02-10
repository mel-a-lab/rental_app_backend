package com.rental.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.sql.Timestamp;
import java.util.List;

@Entity
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String message;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

    @ManyToMany(mappedBy = "receivedMessages")
    private List<User> recipients;


    // Getters
    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public User getSender() {
        return sender;
    }

    public Rental getRental() {
        return rental;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    // Constructors, hashCode, equals, and toString methods (if necessary)
}
