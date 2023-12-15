package org.ssak3.api.badge.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.ssak3.api.badge.entity.OriginBadge;
import org.ssak3.api.badge.service.BadgeService;

import java.util.List;

@Tag(name = "TEST REST API", description = "TEST REST API입니다.")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ledger")
public class BadgeController {

    private final BadgeService badgeService;

    @Operation(summary = "배지 조회", description = "배지 목록을 조회합니다.")
    @GetMapping("/badge")
    public ResponseEntity<List<OriginBadge>> originBadgeList() {

        List<OriginBadge> list;
        try {
            log.info("start select badge list");
            list = badgeService.findAllBadgeList();
        } catch (Exception e) {
            log.error("error", e);
            throw new RuntimeException(e);
        }
        log.info("badge counts: " + list.size());

        return new ResponseEntity<List<OriginBadge>>(list, HttpStatus.OK);
    }

}

//@Tag(name = "BADGE REST API", description = "BADGE REST API입니다.")
//@Slf4j
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/ledger")
//public class BadgeController {
//
//    private final BadgeService badgeService = new BadgeService();
//
//    @Operation(summary = "배지 조회", description = "배지 목록을 조회합니다.")
//    @GetMapping("/badge")
//    public ResponseEntity<List<OriginBadge>> originBadgeList() {
//
//        List<OriginBadge> list;
//        try {
//            log.info("start select badge list");
//            list = badgeService.findAllBadgeList();
//        } catch (Exception e) {
//            log.error("error", e);
//            throw new RuntimeException(e);
//        }
//        log.info("badge counts: " + list.size());
//
//        return new ResponseEntity<List<OriginBadge>>(list, HttpStatus.OK);
//    }
//}
