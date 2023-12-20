package org.ssak3.api.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ssak3.api.category.entity.CustomCategory;
import org.ssak3.api.category.repository.mapping.CustomCategoryMapping;

import java.util.List;

@Repository
public interface CustomCategoryRepository extends JpaRepository<CustomCategory, Long> {

    List<CustomCategoryMapping> findAllByLedgerLedgerId(Long ledgerId);

    void deleteByCustomCategoryId(Long categoryId);

    CustomCategory findByCustomCategoryId(Long categoryId);

    /**
     * 가계부별 커스텀카테고리 목록 오름차순 조회
     * @param ledgerId
     * @return
     */
    @Query("SELECT c FROM CustomCategory c WHERE c.ledger.ledgerId != :#{#ledgerId} ORDER BY c.customCategoryId ASC")
    List<CustomCategory> findAllByLedgerLedgerIdOrderByCustomCategoryIdAsc(Long ledgerId);

    @Query("SELECT c FROM CustomCategory c WHERE c.ledger.ledgerId = :#{#customCategory.ledger.ledgerId} AND c.customCategoryName = :#{#customCategory.customCategoryName}")
    CustomCategory findByLedgerLedgerIdAndCustomCategoryCustomCategoryName(CustomCategory customCategory);
}
