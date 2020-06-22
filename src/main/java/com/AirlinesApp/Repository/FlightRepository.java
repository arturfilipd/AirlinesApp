package com.AirlinesApp.Repository;

import com.AirlinesApp.Model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    @Query(value = "Select * from Flights", nativeQuery = true)
    List<Flight> getAllFlights();
    Flight findOneById(Integer id);

    @Modifying
    @Query("Update Flight f SET f.startDate=:startDate, f.endDate=:endDate  WHERE f.id=:id")
    public void update(@Param("id") Integer id, @Param("startDate") Date starts, @Param("endDate") Date ends);


}
