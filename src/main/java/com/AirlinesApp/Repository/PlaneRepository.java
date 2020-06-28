package com.AirlinesApp.Repository;

import com.AirlinesApp.Model.Airport;
import com.AirlinesApp.Model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repozytorium samolotu
 */
@Repository
public interface PlaneRepository extends JpaRepository<Plane, Integer> {
    /**
     * Zwraca listę samolotów
     * @return Lista samolotów
     */
    @Query(value = "Select * from Planes", nativeQuery = true)
    List<Plane> getAllPlanes();

    /**
     * Zwraca wszystkie samoloty po ID lotniska
     * @param aPID - ID lotniska
     * @return Lista samolotów
     */
    List<Plane> getAllByaPID(Airport aPID);

    /**
     * Zwraca samolot po ID
     * @param id - id
     * @return Plane
     */
    Plane findOneById(Integer id);

}
