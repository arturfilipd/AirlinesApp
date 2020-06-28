package com.AirlinesApp.Repository;

import com.AirlinesApp.Model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Repozytorium lotu
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    /**
     * Zwraca liste wszystkich lotów
     * @return Lista lotów
     */
    @Query(value = "Select * from Flights", nativeQuery = true)
    List<Flight> getAllFlights();

    /**
     * Zwraca lot po ID
     * @param id - id
     * @return Flight
     */
    Flight findOneById(Integer id);

    /**
     * Ustawia daty startu i przylotu
     * @param id - ID
     * @param starts - data odlotu
     * @param ends - data przylotu
     */
    @Modifying
    @Query("Update Flight f SET f.startDate=:startDate, f.endDate=:endDate  WHERE f.id=:id")
    public void update(@Param("id") Integer id, @Param("startDate") Date starts, @Param("endDate") Date ends);


}
