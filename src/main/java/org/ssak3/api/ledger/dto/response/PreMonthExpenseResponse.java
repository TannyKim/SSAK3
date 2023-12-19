package org.ssak3.api.ledger.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PreMonthExpenseResponse {

    @Schema(description = "theme")
    long themeId;

    @Schema(description = "month expense")
    Integer monthExpense; // 월 지출 합산 금액

    public PreMonthExpenseResponse(long themeId, int expense) {
        this.themeId = themeId;
        this.monthExpense = expense;
    }
}
