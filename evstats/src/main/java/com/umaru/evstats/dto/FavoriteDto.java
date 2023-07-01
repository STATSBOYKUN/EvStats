package com.umaru.evstats.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class FavoriteDto {
    private Long id;

    @NotEmpty(message = "Please enter valid userId.")
    private Long userId;

    @NotEmpty(message = "Please enter valid eventId.")
    private Long eventId;
}
