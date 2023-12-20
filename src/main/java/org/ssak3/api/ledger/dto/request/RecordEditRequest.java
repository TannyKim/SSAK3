package org.ssak3.api.ledger.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class RecordEditRequest {

    @Schema(description = "record id", example = "1")
    Long recordId; // 가계부 내역 아이디

    @Schema(description = "category name", example = "교통")
    String categoryName; // 카테고리명

    @Schema(description = "transaction name", example = "홈플러스에서 계란 한판 샀다")
    private String tranName; // 거래명

    @Schema(description = "transaction amount", example = "10000")
    Integer tranAmount; // 거래금액

    @Schema(description = "receipt image url", example = "10000")
    String receiptUrl; // 영수증 URL
}
