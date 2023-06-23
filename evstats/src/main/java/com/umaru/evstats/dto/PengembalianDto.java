package com.umaru.evstats.dto;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PengembalianDto {
    private Long id;
    @NotEmpty(message = "Denda is required.")
    private long denda;
    @NotEmpty(message = "Peminjaman is required.")
    private PeminjamanDto peminjaman;
    @NotEmpty(message = "Status Pengembalian is required")
    private boolean statusPengembalian;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date tanggalPengembalian;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date createdAt;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date updatedAt;
}
