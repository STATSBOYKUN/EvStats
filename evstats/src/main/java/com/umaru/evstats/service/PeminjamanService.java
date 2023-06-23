package com.umaru.evstats.service;

import com.umaru.evstats.dto.PeminjamanDto;
import com.umaru.evstats.model.User;

import java.util.List;

public interface PeminjamanService {
    public List<PeminjamanDto> getPeminjamans();
    public List<PeminjamanDto> getPeminjamans(long id);
    public PeminjamanDto getPeminjaman(Long PeminjamanId);
    public void updatePeminjaman(PeminjamanDto PeminjamanDto);
    public void deletePeminjaman(Long PeminjamanId);
    public void savePeminjaman(PeminjamanDto PeminjamanDto);
    public void savePeminjaman(PeminjamanDto PeminjamanDto, User user, long book_id);
    public void approvePeminjaman(PeminjamanDto peminjamanDto);
    public int getJmlPeminjaman();
}
