package com.ea.bugtracking.service;

import com.ea.bugtracking.entity.Ticket;

public interface TicketService {
    Iterable<Ticket> listTickets();
}


