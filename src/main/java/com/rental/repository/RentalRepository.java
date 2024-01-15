package com.rental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rental.entities.Rental;
import com.rental.entities.User;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

  List<Rental> findAllByOwner(User owner);
}

