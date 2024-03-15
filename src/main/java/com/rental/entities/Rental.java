package com.rental.entities;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Double surface;
    private Double price;
    private String picture;
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "rental")
    private List<Messages> messages;

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getSurface() {
        return surface;
    }

    public Double getPrice() {
        return price;
    }

    public String getPicture() {
        return picture;
    }

    public String getDescription() {
        return description;
    }

    public User getOwner() {
        return owner;
    }

    public List<Messages> getMessages() {
        return messages;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurface(Double surface) {
        this.surface = surface;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setMessages(List<Messages> messages) {
        this.messages = messages;
    }
}
