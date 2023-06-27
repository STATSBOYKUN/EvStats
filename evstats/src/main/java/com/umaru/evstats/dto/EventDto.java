package com.umaru.evstats.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class EventDto {
    private Long id;

    @NotEmpty(message = "Please enter valid event name.")
    private String name;

    @NotEmpty(message = "Please enter valid event details.")
    private String details;

    @NotEmpty(message = "Please enter valid event person.")
    private String person;

    private String additionalDetails;

    @NotEmpty(message = "PLease enter valid event place")
    private String place;

    @NotEmpty(message = "Please enter valid event date.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;
}
