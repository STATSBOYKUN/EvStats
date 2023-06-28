package com.umaru.evstats.service;

import com.umaru.evstats.dto.TicketDto;

import java.util.List;

public interface TicketService {
    public List<TicketDto> getTickets();

    public TicketDto getTicket(Long ticketId);
    public void deleteTicket(Long ticketId);
    public void saveTickets(TicketDto ticketDto);

    public byte[] getInvoice(Long ticketId);
}
