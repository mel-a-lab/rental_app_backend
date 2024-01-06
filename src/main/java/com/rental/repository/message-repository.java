package com.rental.repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByRental(Rental rental);

    List<Message> findAllBySenderAndRental(User sender, Rental rental);
}
