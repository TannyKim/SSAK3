package org.ssak3.api.ledger.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PreMonthExpenseRequest {

    @Schema(description = "theme id", example = "1")
    Long themeId;

    @Schema(description = "last year and month", example = "2023-11")
    String yearMonth;
}
