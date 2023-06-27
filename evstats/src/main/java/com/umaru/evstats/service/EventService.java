package com.umaru.evstats.service;

import com.umaru.evstats.dto.EventDto;

import java.util.List;

public interface EventService {
    public List<EventDto> getEvents();

    public EventDto getEvent(Long eventId);
    public void deleteEvent(Long eventId);
    public void saveEvents(EventDto eventDto);
}
