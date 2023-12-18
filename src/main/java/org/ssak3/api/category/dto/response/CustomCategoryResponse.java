package org.ssak3.api.category.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ssak3.api.category.entity.CustomCategory;

@Data
@NoArgsConstructor
public class CustomCategoryResponse {

    @Schema(description = "카테고리아이디", example = "1")
    Long categoryId;

    @Schema(description = "카테고리이름", example = "배달")
    String categoryName;

    public CustomCategoryResponse(CustomCategory customCategory) {
        this.categoryId = customCategory.getCustomCategoryId();
        this.categoryName = customCategory.getCustomCategoryName();
    }
}
