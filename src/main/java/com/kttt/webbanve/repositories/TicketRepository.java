package com.kttt.webbanve.repositories;

import com.kttt.webbanve.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
