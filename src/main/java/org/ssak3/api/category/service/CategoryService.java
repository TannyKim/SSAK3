package org.ssak3.api.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssak3.api.category.dto.request.CustomCategoryRequest;
import org.ssak3.api.category.dto.response.CategoryListResponse;
import org.ssak3.api.category.entity.CustomCategory;
import org.ssak3.api.category.repository.CustomCategoryRepository;
import org.ssak3.api.category.repository.mapping.CustomCategoryMapping;
import org.ssak3.api.ledger.entity.Ledger;
import org.ssak3.api.ledger.repository.LedgerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CustomCategoryRepository categoryRepository;
    private final LedgerRepository ledgerRepository;

    public CategoryListResponse getCategoryList(long ledgerId) {
        List<CustomCategoryMapping> customCategories = categoryRepository.findAllByLedgerLedgerId(ledgerId);
        return new CategoryListResponse(customCategories);
    }


    @Transactional(readOnly = true)
    public void removeCategory(Long categoryId) {
        categoryRepository.deleteByCustomCategoryId(categoryId);
    }

    public CustomCategory addCategory(CustomCategoryRequest customCategoryRequest) {
        Ledger ledger = ledgerRepository.findByLedgerId(customCategoryRequest.getLedgerId());
        CustomCategory customCategory = new CustomCategory(ledger, customCategoryRequest.getCustomCategoryName());
        return categoryRepository.save(customCategory);
    }
}
