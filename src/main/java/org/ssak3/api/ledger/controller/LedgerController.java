package org.ssak3.api.ledger.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ssak3.api.ledger.dto.request.LedgerEditRequest;
import org.ssak3.api.ledger.dto.response.PreMonthExpenseResponse;
import org.ssak3.api.ledger.entity.Ledger;
import org.ssak3.api.ledger.entity.Theme;
import org.ssak3.api.ledger.service.LedgerService;

import java.util.List;

@Tag(name = "Ledger REST API", description = "Ledger REST API입니다.")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ledger")
public class LedgerController {

    private final LedgerService ledgerService;

    /**
     * 테마 목록 조회
     *
     * @return
     */
    @Operation(summary = "테마 목록 조회", description = "테마 목록을 조회합니다.")
    @GetMapping("/theme/list")
    public ResponseEntity<?> themeList() {

        List<Theme> themeList;

        try {
            log.info("start select all theme list");
            themeList = ledgerService.findThemeList();
        } catch (Exception e) {
            log.error("error", e);
            throw new RuntimeException(e);
        }
        return new ResponseEntity<List<Theme>>(themeList, HttpStatus.OK);
    }

    /**
     * 전월 지출 합산 금액 조회
     *
     * @param themeId
     * @return
     */
    @Operation(summary = "전월 지출 합산 금액 조회", description = "가계부의 테마를 선택하면 해당 테마의 결제내역을 조회하여 전월 지출을 합산합니다.")
    @GetMapping("/theme/add/preMonthExpense")
    public ResponseEntity<?> preMonthExpense(@Param(value = "themeId") Long themeId, @Param(value = "yearMonth") String yearMonth) {

        PreMonthExpenseResponse recordResponse = new PreMonthExpenseResponse();
        PreMonthExpenseResponse preMonthExpense;

        try {
            log.info("start select record list");
            preMonthExpense = ledgerService.findPreMonthExpense(themeId, yearMonth);
//            recordList = ledgerService.findRecordList(recordRequest);
//            recordResponse.setMonthExpense(recordList.stream().mapToInt(Record::getTranAmount).sum());

        } catch (Exception e) {

            log.error("error", e);
            throw new RuntimeException(e);
        }

        return ResponseEntity.status(200).body(preMonthExpense);
    }

    /**
     * 새로운 가계부 생성
     *
     * @param ledger
     * @return
     */
    @Operation(summary = "가계부 생성", description = "새로운 가계부를 생성합니다.")
    @PostMapping("/add")
    public ResponseEntity<?> ledgerAdd(@Valid @RequestBody Ledger ledger) {

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
     * 기존 가계부 편집
     *
     * @param ledgerEditRequest
     * @return
     */
    @Operation(summary = "가계부 편집", description = "기존 가계부를 편집합니다.")
    @PostMapping("/modify")
    public ResponseEntity<?> ledgerModify(@Valid @RequestBody LedgerEditRequest ledgerEditRequest) {

        Ledger ledger;
        try {
            log.info("start update my ledger");
            ledger = ledgerService.modifyLedger(ledgerEditRequest);
        } catch (Exception e) {
            log.error("error", e);
            throw new RuntimeException(e);
        }

        return new ResponseEntity<Ledger>(ledger, HttpStatus.OK);
    }

    /**
     * 기존 가계부 삭제
     *
     * @param ledgerId
     * @return
     */
    @Operation(summary = "가계부 삭제", description = "기존 가계부를 삭제합니다.")
    @DeleteMapping("/remove")
    public void ledgerRemove(@Parameter(name = "ledgerId") Long ledgerId) {

        try {
            log.info("start delete my ledger");
            ledgerService.removeLedger(ledgerId);
        } catch (Exception e) {
            log.error("error", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 나의 가계부 목록 조회
     *
     * @return
     */
    @Operation(summary = "나의 가계부 목록 조회", description = "나의 가계부 목록을 조회합니다.")
    @GetMapping("/my-list")
    public ResponseEntity<?> myLedgerList(Long userId) {

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
     * 나를 제외한 모든 사용자의 가계부 목록 조회
     * @param userId
     * @return
     */
    @Operation(summary = "전체 가계부 목록 조회", description = "나를 제외한 모든 사용자의 가계부 목록을 조회합니다.")
    @GetMapping("/others-list")
    public ResponseEntity<?> othersLedgerList(Long userId) {

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
}
