package com.umaru.evstats.service;

import com.umaru.evstats.dto.KeuanganDto;
import com.umaru.evstats.dto.PengembalianDto;
import com.umaru.evstats.model.Keuangan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface KeuanganService {
    public List<KeuanganDto> getKeuangans();
    public KeuanganDto getKeuangan(Long KeuanganId);
    public Page<Keuangan> findPaginated(Pageable pageable);
    public void updateKeuangan(KeuanganDto KeuanganDto);
    public void deleteKeuangan(Long KeuanganId);
    public void saveKeuangan(KeuanganDto KeuanganDto);
    public void saveKeuangan(PengembalianDto pengembalianDto);
    public int countKeuangans();
    public long getTotalPemasukan();
    public long getTotalPengeluaran();
    public long getTotal();
    public List<Long> getPendapatanBulanan();
    public List<Long> getPengeluaranBulanan();
}
