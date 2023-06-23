package com.umaru.evstats.service;

import com.umaru.evstats.dto.PeminjamanDto;
import com.umaru.evstats.dto.PengembalianDto;

import java.util.Date;
import java.util.List;

public interface PengembalianService {
    public List<PengembalianDto> getPengembalians();
    public List<PengembalianDto> getPengembalians(long id);
    public PengembalianDto getPengembalian(Long PengembalianId);
    public List<PeminjamanDto> getPeminjamanUser(long id);
    public void updatePengembalian(PengembalianDto PengembalianDto);
    public void deletePengembalian(Long PengembalianId);
    public void savePengembalian(PengembalianDto pengembalianDto);
    public void savePengembalian(PeminjamanDto peminjamanDto);
    public void kembalikan(PengembalianDto pengembalianDto, Date tanggalPengembalian);
}
