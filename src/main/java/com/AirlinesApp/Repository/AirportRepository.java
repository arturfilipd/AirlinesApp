package com.AirlinesApp.Repository;


import com.AirlinesApp.Model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Repozytorium lotniska
 */
@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {
    /**
     * Getter lotnisk
     * @return Lista lotnisk
     */
    @Query(value = "Select * from Airports", nativeQuery = true)
    List<Airport> getAllAirports();

    /**
     * Zwraca lotnisko po ID
     * @param id - id
     * @return Airport
     */
    Airport findOneById(Integer id);

    /**
     * Sprawdza czy lotnisko istnieje na podstawie kodu
     * @param code - kod lotniska
     * @return Boolean
     */
    Boolean existsByCode(String code);

    /**
     * Sprawdza czy lotnisko istnieje na podstawie ID
     * @param id - id
     * @return Boolean
     */
    Boolean existsById(Long id);

    /**
     * Zwraca lotnisko po nazwie
     * @param airportName - nazwa lotniska
     * @return Airport
     */
    Airport findOneByAirportName(String airportName);
}
