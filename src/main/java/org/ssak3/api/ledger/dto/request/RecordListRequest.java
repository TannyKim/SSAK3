package org.ssak3.api.ledger.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecordListRequest {

    @Schema(description = "ledger id", example = "1")
    String ledgerId;

    @Schema(description = "now year and month", example = "2023-12")
    String yearMonth;

}
