package com.umaru.evstats.repository;

import com.umaru.evstats.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Favorite findByUserIdAndEventId(Long userId, Long eventId);
    Favorite deleteFavoriteByEventId(Long eventId);
    Favorite deleteFavoriteByUserId(Long userId);
}
