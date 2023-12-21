package org.ssak3.api.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssak3.api.category.entity.OriginCategory;
import org.ssak3.api.ledger.entity.Ledger;

import java.util.List;

@Repository
public interface OriginCategoryRepository extends JpaRepository<OriginCategory, Long> {
    /**
     * 테마별 기본 카테고리 조회
     * @param themeId
     * @return
     */
    List<OriginCategory> findAllByThemeThemeId(Long themeId);


    String findByOriginCategoryName(String originCategoryName);
}
