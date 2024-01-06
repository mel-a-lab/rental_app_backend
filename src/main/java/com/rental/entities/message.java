package com.rental.entities;

@Entity
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

  // Getters and setters omitted for brevity
}
