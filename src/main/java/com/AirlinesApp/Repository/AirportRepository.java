package com.AirlinesApp.Repository;


import com.AirlinesApp.Model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {
    @Query(value = "Select * from Airports", nativeQuery = true)
    List<Airport> getAllAirports();
    Airport findOneById(Integer id);
    Boolean existsByCode(String code);
    Boolean existsById(Long id);
    Airport findOneByAirportName(String airportName);
}
