package org.ssak3.api.ledger.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class RecordRequest {

    @Schema(description = "user id", example = "1")
    String userId;

    @Schema(description = "theme id", example = "1")
    String themeId;

    @Schema(description = "last year and month", example = "2023-11")
    String yearMonth;
}
