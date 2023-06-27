package com.umaru.evstats.service;

import com.umaru.evstats.dto.EventDto;
import com.umaru.evstats.entity.Event;
import com.umaru.evstats.mapper.EventMapper;
import com.umaru.evstats.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventsRepository;

    @Override
    public List<EventDto> getEvents() {
        List<Event> events = eventsRepository.findAll();
        List<EventDto> eventsDto = events.stream()
                .map((event) -> (EventMapper.mapToEventDto(event)))
                .collect(Collectors.toList());

        return eventsDto;
    }

    @Override
    public EventDto getEvent(Long eventId) {
        Optional<Event> events = eventsRepository.findById(eventId);

        if (events.isPresent()){
            return EventMapper.mapToEventDto(events.get());
        } else {
            return null;
        }
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventsRepository.deleteById(eventId);
    }

    @Override
    public void saveEvents(EventDto eventDto) {
        Event event = EventMapper.mapToEvent(eventDto);
        eventsRepository.save(event);
    }
}