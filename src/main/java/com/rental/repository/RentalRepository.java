package com.rental.repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

  List<Rental> findAllByOwner(User owner);
}

