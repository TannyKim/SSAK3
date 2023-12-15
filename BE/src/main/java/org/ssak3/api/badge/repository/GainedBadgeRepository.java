package org.ssak3.api.badge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssak3.api.badge.entity.GainedBadge;

@Repository
public interface GainedBadgeRepository extends JpaRepository<GainedBadge, Long> {
}
