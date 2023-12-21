package org.ssak3.api.badge.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssak3.api.badge.entity.GainedBadge;
import org.ssak3.api.badge.entity.OriginBadge;
import org.ssak3.api.badge.repository.GainedBadgeRepository;
import org.ssak3.api.badge.repository.OriginBadgeRepository;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class BadgeService {

    private final OriginBadgeRepository originBadgeRepository;
    private final GainedBadgeRepository gainedBadgeRepository;

    /**
     * 전체 배지 목록 조회
     * @return
     */
    public List<OriginBadge> findOriginBadgeList() {
        return originBadgeRepository.findAll();
    }



}
