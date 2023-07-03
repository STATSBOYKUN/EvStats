package com.umaru.evstats.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class NotificationDto {
    private Long id;

    @NotEmpty(message = "Please enter valid userId.")
    private Long userId;

    @NotEmpty(message = "Please enter valid notifications.")
    private String notifications;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date createdAt;
}
