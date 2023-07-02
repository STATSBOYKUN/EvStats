package com.umaru.evstats.repository;

import com.umaru.evstats.entity.Help;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface HelpRepository extends JpaRepository<Help, Long> {
}
