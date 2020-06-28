package com.AirlinesApp.Repository;

import com.AirlinesApp.Model.Client;
import com.AirlinesApp.Model.Flight;
import com.AirlinesApp.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repozytorium biletu
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    /**
     * Zwraca wszystkie bilety
     * @return lista biletów
     */
    @Query(value = "Select * from Tickets", nativeQuery = true)
    List<Ticket> getAllTickets();

    /**
     * Zwraca wszsytkie bilety po iD klienta
     * @param clientID - ID klienta
     * @return Lista biletów
     */
    List<Ticket> findAllByClientID(Client clientID);

    /**
     * Zwraca bilet po ID
     * @param ticketID - ID
     * @return Ticket
     */
    Ticket findOneById(Integer ticketID);

    /**
     * Zwraca wszsytkie bilety po ID lotu
     * @param flightID - ID lotu
     * @return Lista biletów
     */
    List<Ticket> findAllByFlightID(Flight flightID);

    /**
     * Ustawia flagę zapłaty oraz miejsce
     * @param paid - flaga zapłaty
     * @param seat - miejsce
     * @param tID - ID biletu
     */
    @Modifying
    @Query("Update Ticket set paid = :paid, seat = :seat where id = :tID")
    public void update(@Param("paid")boolean paid, @Param("seat")Integer seat, @Param("tID")Integer tID);
}
