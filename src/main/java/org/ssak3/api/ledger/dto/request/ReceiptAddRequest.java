package org.ssak3.api.ledger.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ReceiptAddRequest {

    @Schema(description = "record id", example = "1")
    private long recordId;

    @Schema(description = "image", example = "file")
    private MultipartFile image;
}
