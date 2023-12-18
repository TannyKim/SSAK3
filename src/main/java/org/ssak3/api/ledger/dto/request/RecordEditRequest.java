package org.ssak3.api.ledger.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecordEditRequest {

    @Schema(description = "record id", example = "1")
    Long recordId;

    @Schema(description = "categoryId", example = "1")
    Long categoryId;

    @Schema(description = "categoryName", example = "교통")
    String categoryName;

    @Schema(description = "tranName", example = "홈플러스에서 계란 한판 샀다")
    private String tranName; // 거래명

    @Schema(description = "tranAmount", example = "10000")
    int tranAmount;


}
