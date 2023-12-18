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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssak3.api.category.dto.response.CategoryListResponse;
import org.ssak3.api.ledger.dto.request.RecordListRequest;
import org.ssak3.api.ledger.dto.response.RecordListResponse;
import org.ssak3.api.ledger.repository.mapping.RecordMapping;
import org.ssak3.api.ledger.service.RecordService;

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
                    array = @ArraySchema(schema = @Schema(implementation = CategoryListResponse.class)))),
            @ApiResponse(responseCode = "404", description = "가계부 내역 목록 조회 실패")
    })
    @PostMapping("/list")
    public ResponseEntity<?> recordList(@Valid @RequestBody RecordListRequest recordListRequest) {
        List<RecordMapping> allRecordByYearAndMonth = recordService.findAllRecordByYearAndMonth(recordListRequest);
        RecordListResponse recordListResponse = new RecordListResponse(allRecordByYearAndMonth);
        return ResponseEntity.status(200).body(recordListResponse);
    }


}
