package org.ssak3.api.badge.repository;

import org.ssak3.api.badge.entity.OriginBadge;

import java.util.List;

public interface BadgeRepository {
    /**
     * 전체 배지 조회
     *
     * @return
     */
    List<OriginBadge> findAllBadgeList();

}
