package com.umaru.evstats.mapper;

import com.umaru.evstats.dto.EventDto;
import com.umaru.evstats.entity.Event;

public class EventMapper {
    public static EventDto mapToEventDto(Event event) {
        EventDto eventDto = EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .details(event.getDetails())
                .person(event.getPerson())
                .additionalDetails(event.getAdditionalDetails())
                .date(event.getDate())
                .place(event.getPlace())
                .build();
        return eventDto;
    }
    public static Event mapToEvent(EventDto eventDto) {
        Event event = Event.builder()
                .id(eventDto.getId())
                .name(eventDto.getName())
                .details(eventDto.getDetails())
                .person(eventDto.getPerson())
                .additionalDetails(eventDto.getAdditionalDetails())
                .date(eventDto.getDate())
                .place(eventDto.getPlace())
                .build();
        return event;
    }
}
