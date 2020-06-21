package com.AirlinesApp.Repository;

import com.AirlinesApp.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query(value = "Select * from Clients", nativeQuery = true)
    List<Client> getAllClients();
}
