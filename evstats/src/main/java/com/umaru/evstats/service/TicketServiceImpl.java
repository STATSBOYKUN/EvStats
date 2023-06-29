package com.umaru.evstats.service;

import com.umaru.evstats.dto.TicketDto;
import com.umaru.evstats.entity.Ticket;
import com.umaru.evstats.mapper.TicketMapper;
import com.umaru.evstats.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public void saveTickets(TicketDto ticketDto, MultipartFile imageFile) throws IOException {
        String folder = "src/main/resources/static/invoices/";
        byte[] bytes = imageFile.getBytes();
        Path path = Paths.get(folder + imageFile.getOriginalFilename()).toAbsolutePath();
        Files.write(path, bytes);

        String invoices = imageFile.getOriginalFilename();
        ticketDto.setInvoices(invoices);
        ticketDto.setStatus("Pending");
        ticketDto.setEmail("a");
        Ticket ticket = TicketMapper.mapToTicket(ticketDto);

        ticketsRepository.save(ticket);
    }

    @Override
    public void saveTicket(TicketDto ticketDto) {
        Ticket ticket = TicketMapper.mapToTicket(ticketDto);
        ticketsRepository.save(ticket);
    }
}
