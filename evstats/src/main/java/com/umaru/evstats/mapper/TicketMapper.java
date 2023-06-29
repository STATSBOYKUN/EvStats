package com.umaru.evstats.mapper;

import com.umaru.evstats.dto.TicketDto;
import com.umaru.evstats.entity.Ticket;

public class TicketMapper {
    public static TicketDto mapToTicketDto(Ticket ticket) {
        TicketDto ticketDto = TicketDto.builder()
                .id(ticket.getId())
                .name(ticket.getName())
                .email(ticket.getEmail())
                .invoices(ticket.getInvoices())
                .tickets(ticket.getTickets())
                .date(ticket.getDate())
                .status(ticket.getStatus())
                .build();
        return ticketDto;
    }

    public static Ticket mapToTicket(TicketDto ticketDto) {
        Ticket ticket = Ticket.builder()
                .id(ticketDto.getId())
                .name(ticketDto.getName())
                .email(ticketDto.getEmail())
                .invoices(ticketDto.getInvoices())
                .tickets(ticketDto.getTickets())
                .date(ticketDto.getDate())
                .status(ticketDto.getStatus())
                .build();
        return ticket;
    }
}
