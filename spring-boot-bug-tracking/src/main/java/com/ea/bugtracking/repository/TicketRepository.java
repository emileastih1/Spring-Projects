package com.ea.bugtracking.repository;

import org.springframework.data.repository.CrudRepository;

import com.ea.bugtracking.entity.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
}
