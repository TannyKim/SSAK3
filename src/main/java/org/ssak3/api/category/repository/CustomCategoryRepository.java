package org.ssak3.api.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssak3.api.category.entity.CustomCategory;
import org.ssak3.api.category.repository.mapping.CustomCategoryMapping;

import java.util.List;

public interface CustomCategoryRepository extends JpaRepository<CustomCategory, Long> {

    List<CustomCategoryMapping> findAllByLedgerLedgerId(long ledgerId);

    void deleteByCustomCategoryId(Long categoryId);
}
