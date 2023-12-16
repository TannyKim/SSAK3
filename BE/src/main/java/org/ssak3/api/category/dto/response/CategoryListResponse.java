package org.ssak3.api.category.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ssak3.api.category.repository.mapping.CustomCategoryMapping;

import java.util.List;

@Data
@NoArgsConstructor
public class CategoryListResponse {

    @Schema(description = "카테고리 리스트")
    List<CustomCategoryMapping> categoryList;

    public CategoryListResponse(List<CustomCategoryMapping> categoryList) {
        this.categoryList = categoryList;
    }
}
