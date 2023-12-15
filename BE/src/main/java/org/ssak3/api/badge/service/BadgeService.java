package org.ssak3.api.badge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssak3.api.badge.entity.OriginBadge;
import org.ssak3.api.badge.repository.GainedBadgeRepository;
import org.ssak3.api.badge.repository.OriginBadgeRepository;

import java.util.List;

@Service
public class BadgeService {

    @Autowired
    private OriginBadgeRepository originBadgeRepository;
    @Autowired
    private GainedBadgeRepository gainedBadgeRepository;

    public List<OriginBadge> findAllBadgeList() {
        return originBadgeRepository.findAll();
    }
}
