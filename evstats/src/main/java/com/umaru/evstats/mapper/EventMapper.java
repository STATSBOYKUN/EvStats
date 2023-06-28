package com.umaru.evstats.mapper;

import com.umaru.evstats.dto.EventDto;
import com.umaru.evstats.entity.Event;

public class EventMapper {
    public static EventDto mapToEventDto(Event event) {
        EventDto eventDto = EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .thumbnail(event.getThumbnail())
                .details(event.getDetails())
                .person(event.getPerson())
                .additionalDetails(event.getAdditionalDetails())
                .date(event.getDate())
                .time(event.getTime())
                .place(event.getPlace())
                .price(event.getPrice())
                .build();
        return eventDto;
    }
    public static Event mapToEvent(EventDto eventDto) {
        Event event = Event.builder()
                .id(eventDto.getId())
                .name(eventDto.getName())
                .thumbnail(eventDto.getThumbnail())
                .details(eventDto.getDetails())
                .person(eventDto.getPerson())
                .additionalDetails(eventDto.getAdditionalDetails())
                .date(eventDto.getDate())
                .time(eventDto.getTime())
                .place(eventDto.getPlace())
                .price(eventDto.getPrice())
                .build();
        return event;
    }
}
