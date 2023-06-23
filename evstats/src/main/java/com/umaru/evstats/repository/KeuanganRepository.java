package com.umaru.evstats.repository;

import com.umaru.evstats.model.Keuangan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeuanganRepository extends JpaRepository <Keuangan, Long>{
}
