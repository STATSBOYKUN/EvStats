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

public class HelpDto {
    private Long id;

    @NotEmpty(message = "Please enter valid name.")
    private String name;

    @NotEmpty(message = "Please enter valid email.")
    private String email;

    @NotEmpty(message = "Please enter valid message.")
    private String message;

    @NotEmpty(message = "Please enter valid date.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;
}
