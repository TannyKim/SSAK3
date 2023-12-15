package org.ssak3.api.ledger.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssak3.api.badge.entity.OriginBadge;
import org.ssak3.api.ledger.entity.Ledger;
import org.ssak3.api.ledger.service.LedgerService;

import java.util.List;

@Tag(name="Ledger REST API", description = "Ledger REST API입니다.")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ledger")
public class LedgerController {

    @Autowired
    private final LedgerService ledgerService;

    /** 나를 제외한 모든 사용자의 가계부 목록 조회
     *
     * @return
     */
    @Operation(summary = "전체 가계부 목록 조회", description = "나를 제외한 모든 사용자의 가계부 목록을 조회합니다.")
    @GetMapping("/others-list")
    public ResponseEntity<List<Ledger>> othersLedgerList(@Parameter(name="userId") Long userId) {

        List<Ledger> list;
        try {
            log.info("start select all ledger list");
            list = ledgerService.findOthersLedgerList(userId);
        } catch (Exception e) {
            log.error("error", e);
            throw new RuntimeException(e);
        }
        log.info("all ledger counts: " + list.size());

        return new ResponseEntity<List<Ledger>>(list, HttpStatus.OK);
    }

    /**
     * 나의 가계부 목록 조회
     * @return
     */
    @Operation(summary = "나의 가계부 목록 조회", description = "나의 가계부 목록을 조회합니다.")
    @GetMapping("/my-list")
    public ResponseEntity<List<Ledger>> myLedgerList(@Parameter(name="userId") Long userId) {

        List<Ledger> list;
        try {
            log.info("start select my ledger list");
            list = ledgerService.findMyLedgerList(userId);
        } catch (Exception e) {
            log.error("error", e);
            throw new RuntimeException(e);
        }
        log.info("my ledger counts: " + list.size());

        return new ResponseEntity<List<Ledger>>(list, HttpStatus.OK);
    }

    /**
     * 새로운 가계부 생성
     * @param ledger
     * @return
     */
    @Operation(summary = "가계부 생성", description = "새로운 가계부를 생성합니다.")
    @PostMapping("/add")
    public ResponseEntity<Ledger> ledgerAdd(@Parameter(name="ledger") Ledger ledger) {

        try {
            log.info("start insert new ledger");
            ledger = ledgerService.addLedger(ledger);
        } catch (Exception e) {
            log.error("error", e);
            throw new RuntimeException(e);
        }

        return new ResponseEntity<Ledger>(ledger, HttpStatus.OK);
    }

    /**
     * 기존 가계부 편집(수정)
     * @param ledger
     * @return
     */
    @Operation(summary = "가계부 편집", description = "기존 가계부를 편집합니다.")
    @PostMapping("/modify")
    public ResponseEntity<Ledger> ledgerModify(@Parameter(name="ledger") Ledger ledger) {

        try {
            log.info("start update my ledger");
            ledger = ledgerService.modifyLedger(ledger);
        } catch (Exception e) {
            log.error("error", e);
            throw new RuntimeException(e);
        }

        return new ResponseEntity<Ledger>(ledger, HttpStatus.OK);
    }

    /**
     * 기존 가계부 삭제
     * @param ledgerId
     * @return
     */
    @Operation(summary = "가계부 삭제", description = "기존 가계부를 삭제합니다.")
    @PostMapping("/remove")
    public void ledgerRemove(@Parameter(name="ledgerId") Long ledgerId) {

        try {
            log.info("start delete my ledger");
            ledgerService.removeLedger(ledgerId);
        } catch (Exception e) {
            log.error("error", e);
            throw new RuntimeException(e);
        }
    }
}
