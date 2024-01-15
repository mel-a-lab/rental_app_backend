package com.rental.entities;

@Entity
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
  private User owner;

  @OneToMany(mappedBy = "rental")
  private List<Message> messages;

}
