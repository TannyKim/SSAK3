package org.ssak3.api.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssak3.api.category.entity.CustomCategory;
import org.ssak3.api.category.repository.mapping.CustomCategoryMapping;

import java.util.List;

@Repository
public interface CustomCategoryRepository extends JpaRepository<CustomCategory, Long> {

    List<CustomCategoryMapping> findAllByLedgerLedgerId(Long ledgerId);

    void deleteByCustomCategoryId(Long categoryId);

    CustomCategory findByCustomCategoryId(Long categoryId);
}
