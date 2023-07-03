package com.umaru.evstats.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

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
