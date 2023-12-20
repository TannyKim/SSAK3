package org.ssak3.api.ledger.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LedgerEditRequest {

    @Schema(description = "ledger id", example = "1")
    Long ledgerId; // 가계부 아이디

    @Schema(description = "ledger goal", example = "내 집 마련")
    String goal; // 가계부 목표(제목)

    @Schema(description = "monthly budget", example = "1000000")
    private Long monthBudget; // 월 예산

    @Schema(description = "private or public", example = "1")
    private String isPublic; // 가계부 공개여부

}
