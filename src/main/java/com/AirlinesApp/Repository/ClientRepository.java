package com.AirlinesApp.Repository;

import com.AirlinesApp.Model.Client;
import com.AirlinesApp.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repozytorium klienta
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    /**
     * Getter klientów
      * @return Lista klientów
     */
    @Query(value = "Select * from Clients", nativeQuery = true)
    List<Client> getAllClients();
    /**
     * Zwraca klienta po id użytkownika
     * @param userId - id użytkownika
     * @return Client
     */
    Client findOneByUserId(User userId);

    /**
     * Zwraca klienta po ID
     * @param id - id
     * @return Client
     */
    Client findOneById(Integer id);
}
