package org.ssak3.api.category.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.ssak3.api.category.dto.request.CustomCategoryRequest;
import org.ssak3.api.category.dto.response.CategoryListResponse;
import org.ssak3.api.category.dto.response.CustomCategoryResponse;
import org.ssak3.api.category.entity.CustomCategory;
import org.ssak3.api.category.service.CategoryService;

@Tag(name="Category REST API", description = "Category REST API입니다.")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "카테고리 목록 조회", description = "해당 가계부의 카테고리 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "카테고리 조회 성공", content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = CategoryListResponse.class)))),
            @ApiResponse(responseCode = "404", description = "카테고리 조회 실패")
    })
    @PostMapping("")
    public ResponseEntity<?> category(Long ledgerId) throws Exception {
        CategoryListResponse categoryList = categoryService.getCategoryList(ledgerId);
        return ResponseEntity.status(200).body(categoryList);
    }

    @Operation(summary = "카테고리 삭제", description = "해당 카테고리를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "카테고리 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "카테고리 삭제 실패")
    })
    @DeleteMapping("{categoryId}")
    public ResponseEntity<?> categoryRemove(Long categoryId) throws Exception {
        categoryService.removeCategory(categoryId);
        String result = "삭제되었습니다.";
        return ResponseEntity.status(200).body(result);
    }

    @Operation(summary = "카테고리 등록", description = "해당 카테고리를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "카테고리 등록 성공", content = @Content(schema = @Schema(implementation = CategoryListResponse.class))),
            @ApiResponse(responseCode = "404", description = "카테고리 등록 실패")
    })
    @PostMapping("/create")
    public ResponseEntity<?> categoryAdd(@Valid @RequestBody CustomCategoryRequest customCategoryRequest) throws Exception {
        CustomCategory customCategory = categoryService.addCategory(customCategoryRequest);
        CustomCategoryResponse customCategoryResponse = new CustomCategoryResponse(customCategory);
        return ResponseEntity.status(200).body(customCategoryResponse);
    }
}
