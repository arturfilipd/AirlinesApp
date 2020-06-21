package com.AirlinesApp.Repository;

import com.AirlinesApp.Model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaneRepository extends JpaRepository<Plane, Integer> {
    @Query(value = "Select * from Planes", nativeQuery = true)
    List<Plane> getAllPlanes();

}
