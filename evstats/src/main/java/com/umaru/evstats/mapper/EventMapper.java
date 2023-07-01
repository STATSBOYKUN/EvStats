package com.umaru.evstats.mapper;

import com.umaru.evstats.dto.EventDto;
import com.umaru.evstats.dto.FavoriteDto;
import com.umaru.evstats.entity.Event;
import com.umaru.evstats.entity.Favorite;

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
                .poster(event.getPoster())
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
                .poster(eventDto.getPoster())
                .build();
        return event;
    }

    public static FavoriteDto mapToFavoriteDto(Favorite favorite) {
        FavoriteDto favoriteDto = FavoriteDto.builder()
                .id(favorite.getId())
                .userId(favorite.getUserId())
                .eventId(favorite.getEventId())
                .build();
        return favoriteDto;
    }

    public static Favorite mapToFavorite(FavoriteDto favoriteDto) {
        Favorite favorite = Favorite.builder()
                .id(favoriteDto.getId())
                .userId(favoriteDto.getUserId())
                .eventId(favoriteDto.getEventId())
                .build();
        return favorite;
    }
}
