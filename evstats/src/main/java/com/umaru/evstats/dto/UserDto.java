package com.umaru.evstats.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    @NotEmpty(message = "Please enter valid name.")
    private String username;

    @NotEmpty(message = "Please enter valid email.")
    @Email
    private String email;

    @NotEmpty(message = "Please enter valid password.")
    private String password;

    @NotEmpty(message = "Please enter valid provinsi.")
    private String provinsi;

    @NotEmpty(message = "Please enter valid pekerjaan.")
    private String pekerjaan;

    @NotNull(message = "Please enter valid umur.")
    private Integer umur;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date createdAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date updatedAt;
}
