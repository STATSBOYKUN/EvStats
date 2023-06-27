package com.umaru.evstats.service;

import com.umaru.evstats.dto.TicketDto;
import com.umaru.evstats.entity.Ticket;
import com.umaru.evstats.mapper.TicketMapper;
import com.umaru.evstats.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketsRepository;

    @Override
    public List<TicketDto> getTickets() {
        List<Ticket> tickets = ticketsRepository.findAll();
        List<TicketDto> ticketDto = tickets.stream()
                .map((ticket) -> (TicketMapper.mapToTicketDto(ticket)))
                .collect(Collectors.toList());

        return ticketDto;
    }

    @Override
    public TicketDto getTicket(Long ticketId) {
        Optional<Ticket> tickets = ticketsRepository.findById(ticketId);
        if (tickets.isPresent()){
            return TicketMapper.mapToTicketDto(tickets.get());
        } else {
            return null;
        }
    }

    @Override
    public void deleteTicket(Long ticketId) {
        ticketsRepository.deleteById(ticketId);
    }

    @Override
    public void saveTickets(TicketDto ticketDto) {
        Ticket ticket = TicketMapper.mapToTicket(ticketDto);
        ticketsRepository.save(ticket);
    }
}
