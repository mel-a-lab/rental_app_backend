package com.rental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rental.entities.Message;
import com.rental.entities.Rental;
import com.rental.entities.User;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByRental(Rental rental);

    List<Message> findAllBySenderAndRental(User sender, Rental rental);
}
