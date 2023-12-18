package org.ssak3.api.category.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomCategoryRequest {

    @Schema(description = "가계부아이디", example = "1")
    Long ledgerId;

    @Schema(description = "카테고리 이름", example = "배달")
    String customCategoryName;
}
