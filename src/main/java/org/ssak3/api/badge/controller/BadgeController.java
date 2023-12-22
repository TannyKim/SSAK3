package org.ssak3.api.badge.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ssak3.api.badge.dto.request.BadgeRequest;
import org.ssak3.api.badge.entity.OriginBadge;
import org.ssak3.api.badge.service.BadgeService;

import javax.lang.model.util.Elements;
import java.util.List;

@Tag(name = "Badge REST API", description = "Badge REST API입니다.")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/badge")
public class BadgeController {

    private final BadgeService badgeService;

    @Operation(summary = "전체 배지 목록 조회", description = "전체 배지 목록을 조회합니다.")
    @GetMapping("/list")
    public ResponseEntity<List<OriginBadge>> badgeList() {

        List<OriginBadge> list;
        try {
            log.info("start select badge list");
            list = badgeService.findBadgeList();
        } catch (Exception e) {
            log.error("error", e);
            throw new RuntimeException(e);
        }
        log.info("origin badge counts: " + list.size());

        return new ResponseEntity<List<OriginBadge>>(list, HttpStatus.OK);
    }

    @Operation(summary = "배지 부착 여부 수정", description = "배지 부착 여부를 수정합니다.")
    @PostMapping("/modify")
    public ResponseEntity<OriginBadge> badgeModify(@Valid @RequestBody BadgeRequest badgeRequest) {

        OriginBadge badge;
        try {
            log.info("start select badge list");
            badge = badgeService.modifyBadgeIsFixed(badgeRequest);
        } catch (Exception e) {
            log.error("error", e);
            throw new RuntimeException(e);
        }

        return new ResponseEntity<OriginBadge>(badge, HttpStatus.OK);
    }

}
