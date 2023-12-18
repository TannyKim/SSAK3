package org.ssak3.api.badge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssak3.api.badge.entity.OriginBadge;

@Repository
public interface OriginBadgeRepository extends JpaRepository<OriginBadge, Long> {
}
