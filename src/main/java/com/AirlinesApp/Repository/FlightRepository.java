package com.AirlinesApp.Repository;

import com.AirlinesApp.Model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    @Query(value = "Select * from Flights", nativeQuery = true)
    List<Flight> getAllFlights();
}
