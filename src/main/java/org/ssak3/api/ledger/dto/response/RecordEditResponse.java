package org.ssak3.api.ledger.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ssak3.api.ledger.entity.Record;

@Data
@NoArgsConstructor
public class RecordEditResponse {

    @Schema(description = "record Id", example = "1")
    Long recordId;

    @Schema(description = "categoryName", example = "교통")
    String categoryName;

    @Schema(description = "tranName", example = "거래명")
    String tranName;

    @Schema(description = "tran Amount", example = "12,300")
    int tranAmount;

    @Schema(description = "tranYmd", example = "2023-12-15")
    String tranYmd;

    @Schema(description = "tranTime", example = "13:45")
    String tranTime;

    @Schema(description = "tranPlace", example = "김명자 굴국밥")
    String tranPlace;

    @Schema(description = "isExpense", example = "1")
    String isExpense;

    @Schema(description = "receiptUrl", example = "")
    String receiptUrl;

    public RecordEditResponse(Record record) {
        this.recordId = record.getRecordId();
        this.categoryName = record.getCategoryName();
        this.tranAmount = record.getTranAmount();
        this.tranPlace = record.getTranPlace();
        this.isExpense = record.getIsExpense();
        this.tranName = record.getTranName();
        this.tranYmd = record.getTranYmd();
        this.tranTime = record.getTranTime();
        this.receiptUrl = record.getReceiptUrl();
    }
}
