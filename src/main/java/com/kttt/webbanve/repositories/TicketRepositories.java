package com.kttt.webbanve.repositories;

import com.kttt.webbanve.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepositories extends JpaRepository<Ticket,Integer> {

}
