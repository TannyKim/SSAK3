package org.ssak3.api.ledger.repository.mapping;

import io.swagger.v3.oas.annotations.media.Schema;

public interface RecordMapping {

    @Schema(description = "record Id", example = "1")
    Long getRecordId();

    @Schema(description = "categoryName", example = "교통")
    String getCategoryName();

    @Schema(description = "tranName", example = "거래명")
    String getTranName();

    @Schema(description = "tran Amount", example = "12,300")
    int getTranAmount();

    @Schema(description = "tranYmd", example = "2023-12-15")
    String getTranYmd();

    @Schema(description = "tranTime", example = "13:45")
    String getTranTime();

    @Schema(description = "tranPlace", example = "김명자 굴국밥")
    String getTranPlace();

    @Schema(description = "isExpense", example = "1")
    String getIsExpense();

    @Schema(description = "receipt url", example = "https://ssak3.s3.ap-northeast-2.amazonaws.com/images/1-Screenshot_20220422183600.jpg")
    String getReceiptUrl();
}
