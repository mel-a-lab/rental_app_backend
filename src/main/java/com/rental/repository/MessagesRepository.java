package com.rental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rental.entities.Messages;
import com.rental.entities.Rental;
import com.rental.entities.User;

@Repository
public interface MessagesRepository extends JpaRepository<Messages, Long> {

    List<Messages> findAllByRental(Rental rental);

    List<Messages> findAllBySenderAndRental(User sender, Rental rental);
}
