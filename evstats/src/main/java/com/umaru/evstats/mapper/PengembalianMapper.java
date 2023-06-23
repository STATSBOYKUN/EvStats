package com.umaru.evstats.mapper;

import com.umaru.evstats.dto.PengembalianDto;
import com.umaru.evstats.model.Pengembalian;

public class PengembalianMapper {
    public static PengembalianDto mapToPengembalianDto(Pengembalian pengembalian) {
        PengembalianDto pengembalianDto = PengembalianDto.builder()
                .id(pengembalian.getId())
                .denda(pengembalian.getDenda())
                .peminjaman(PeminjamanMapper.mapToPeminjamanDto(pengembalian.getPeminjaman()))
                .statusPengembalian(pengembalian.isStatusPengembalian())
                .tanggalPengembalian(pengembalian.getTanggalPengembalian())
                .createdAt(pengembalian.getCreatedAt())
                .updatedAt(pengembalian.getUpdatedAt())
                .build();
        return pengembalianDto;
    }
    public static Pengembalian mapToPengembalian(PengembalianDto pengembalianDto) {
        Pengembalian pengembalian = Pengembalian.builder()
                .id(pengembalianDto.getId())
                .denda(pengembalianDto.getDenda())
                .peminjaman(PeminjamanMapper.mapToPeminjaman(pengembalianDto.getPeminjaman()))
                .tanggalPengembalian(pengembalianDto.getTanggalPengembalian())
                .statusPengembalian(pengembalianDto.isStatusPengembalian())
                .createdAt(pengembalianDto.getCreatedAt())
                .updatedAt(pengembalianDto.getUpdatedAt())
                .build();
        return pengembalian;
    }
}
