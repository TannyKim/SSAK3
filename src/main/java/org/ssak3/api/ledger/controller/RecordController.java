package org.ssak3.api.ledger.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.ssak3.api.ledger.dto.request.ReceiptAddRequest;
import org.ssak3.api.ledger.dto.request.RecordAddRequest;
import org.ssak3.api.ledger.dto.request.RecordEditRequest;
import org.ssak3.api.ledger.dto.request.RecordListRequest;
import org.ssak3.api.ledger.dto.response.RecordEditResponse;
import org.ssak3.api.ledger.dto.response.RecordListResponse;
import org.ssak3.api.ledger.entity.Record;
import org.ssak3.api.ledger.repository.mapping.RecordMapping;
import org.ssak3.api.ledger.service.RecordService;

import java.io.IOException;
import java.util.List;

@Tag(name = "Ledger REST API", description = "Ledger REST API입니다.")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/record")
public class RecordController {

    private final RecordService recordService;

    @Operation(summary = "가계부 내역 목록 조회", description = "가계부 내역 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "가계부 내역 목록 조회 성공", content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = RecordListResponse.class)))),
            @ApiResponse(responseCode = "404", description = "가계부 내역 목록 조회 실패")
    })
    @PostMapping("/list")
    public ResponseEntity<?> recordList(@Valid @RequestBody RecordListRequest recordListRequest) {
        List<RecordMapping> allRecordByYearAndMonth = recordService.findAllRecordByYearAndMonth(recordListRequest);
        RecordListResponse recordListResponse = new RecordListResponse(allRecordByYearAndMonth);
        return ResponseEntity.status(200).body(recordListResponse);
    }

    @Operation(summary = "가계부 내역 추가", description = "가계부 내역을 수동으로 추가합니다.")
    @PostMapping("/add")
    public ResponseEntity<?> recordAdd(@Valid @RequestBody RecordAddRequest recordAddRequest) {
        Record record = recordService.addRecord(recordAddRequest);
        return ResponseEntity.status(200).body(record);
    }

    @Operation(summary = "가계부 내역 수정", description = "가계부 내역을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "가계부 내역 수정 성공", content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = RecordEditResponse.class)))),
            @ApiResponse(responseCode = "404", description = "가계부 내역 수정 실패")
    })
    @PostMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<?> recordEdit(@Valid @RequestBody RecordEditRequest recordEditRequest) throws IOException {
        RecordEditResponse response = recordService.modifyRecord(recordEditRequest);
        return ResponseEntity.status(200).body(response);
    }

    @Operation(summary = "가계부 내역 영수증 등록", description = "가계부 내역의 영수증을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "가계부 내역 영수증 등록 성공"),
            @ApiResponse(responseCode = "404", description = "가계부 내역 영수증 등록 실패 실패")
    })
    @PostMapping(value = "/upload")
    public ResponseEntity<?> recordReceiptAdd(@Valid @RequestPart(value = "recordId") ReceiptAddRequest receiptAddRequest, @RequestPart MultipartFile image) throws IOException {
//        String response = recordService.addRecordReceipt(receiptAddRequest.getRecordId(), receiptAddRequest.getImage());
        String response = recordService.addRecordReceipt(receiptAddRequest.getRecordId(), image);
        return ResponseEntity.status(200).body(response);
    }

    @Operation(summary = "가계부 내역 삭제", description = "가계부 내역을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "가계부 내역 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "가계부 내역 삭제 실패")
    })
    @DeleteMapping("")
    public ResponseEntity<?> recordRemove(@RequestParam(value = "recordId") Long recordId) {
        recordService.deleteRecord(recordId);
        String result = "삭제되었습니다.";
        return ResponseEntity.status(200).body(result);
    }


}
