package org.ssak3.api.ledger.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ssak3.api.ledger.entity.Theme;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class RecordResponse {

    @Schema(description = "theme")
    Theme theme;

    @Schema(description = "record list")
    List<Record> recordList;

    @Schema(description = "month expense")
    Integer monthExpense; // 월 지출 합산
}
