package com.umaru.evstats.service;

import com.umaru.evstats.dto.TicketDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TicketService {
    public List<TicketDto> getTickets();

    public TicketDto getTicket(Long ticketId);

    public void deleteTicket(Long ticketId);

    public void saveTickets(TicketDto ticketDto, MultipartFile imageFile) throws IOException;

    public void saveTicket(TicketDto ticketDto);
}
