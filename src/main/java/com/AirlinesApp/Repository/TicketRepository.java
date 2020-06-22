package com.AirlinesApp.Repository;

import com.AirlinesApp.Model.Client;
import com.AirlinesApp.Model.Ticket;
import org.junit.FixMethodOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    @Query(value = "Select * from Tickets", nativeQuery = true)
    List<Ticket> getAllTickets();

    List<Ticket> findAllByClientID(Client clientID);
    Ticket findOneByid(Integer ticketID);

    @Modifying
    @Query("Update Ticket set paid = :paid, seat = :seat where id = :tID")
    public void update(@Param("paid")boolean paid, @Param("seat")Integer seat, @Param("tID")Integer tID);
}
