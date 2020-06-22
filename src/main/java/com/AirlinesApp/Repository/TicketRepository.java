package com.AirlinesApp.Repository;

import com.AirlinesApp.Model.Client;
import com.AirlinesApp.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    @Query(value = "Select * from Tickets", nativeQuery = true)
    List<Ticket> getAllTickets();

    List<Ticket> findAllByClientID(Client clientID);
}
