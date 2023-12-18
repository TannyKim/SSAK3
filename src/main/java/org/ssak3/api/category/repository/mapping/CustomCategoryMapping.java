package org.ssak3.api.category.repository.mapping;

import io.swagger.v3.oas.annotations.media.Schema;

public interface CustomCategoryMapping {

    @Schema(example = "1")
    Long getCustomCategoryId();

    @Schema(example = "배달")
    String getCustomCategoryName();
}
