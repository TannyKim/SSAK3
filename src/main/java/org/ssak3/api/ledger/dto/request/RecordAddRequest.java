package org.ssak3.api.ledger.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class RecordAddRequest {

    @Schema(description = "ledger id", example = "1")
    Long ledgerId; // 가계부 아이디

    @Schema(description = "category name", example = "교통")
    String categoryName; // 카테고리명

    @Schema(description = "transaction name", example = "홈플러스에서 계란 한판 샀다")
    private String tranName; // 거래명

    @Schema(description = "transaction Amount", example = "10000")
    int tranAmount; // 거래금액

    @Schema(description = "transaction Amount", example = "10000")
    private String tranYmd; // 거래년월일

    @Schema(description = "transaction Amount", example = "10000")
    private String tranTime; // 거래시각

    @Schema(description = "transaction Amount", example = "10000")
    private String tranPlace; // 상호명

    @Schema(description = "income or expense", example = "1")
    String isExpense; // 수입 or 지출

    @Schema(description = "receipt image url", example = "10000")
    String receiptUrl; // 영수증 URL
}
