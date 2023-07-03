package com.umaru.evstats.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class EventDto {
    private Long id;

    @NotEmpty(message = "Please enter valid event name.")
    private String name;

    @NotEmpty(message = "Please enter valid event thumbnail.")
    private String thumbnail;

    @NotEmpty(message = "Please enter valid event details.")
    private String details;

    @NotEmpty(message = "Please enter valid event person.")
    private String person;

    private String additionalDetails;

    @NotEmpty(message = "PLease enter valid event place")
    private String place;

    @NotEmpty(message = "Please enter valid event date.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @NotEmpty(message = "Please enter valid event time.")
    private LocalTime time;

    @NotEmpty(message = "Please enter valid event ticket price.")
    private Integer price;

    @NotEmpty(message = "Please enter valid event poster.")
    private String poster;
}
