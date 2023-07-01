package com.umaru.evstats.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

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
    private Long notifications;

    @NotEmpty(message = "Please enter valid read.")
    private Long read;
}
