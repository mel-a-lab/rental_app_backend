package com.rental.entities;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String email;
  private String name;
  private String password;
  private Timestamp createdAt;
  private Timestamp updatedAt;

  @OneToMany(mappedBy = "owner")
  private List<Rental> rentals;

  @OneToMany(mappedBy = "sender")
  private List<Message> sentMessages;

  @ManyToMany(mappedBy = "recipients")
  private List<Message> receivedMessages;

}

