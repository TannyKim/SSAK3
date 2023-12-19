package org.ssak3.api.ledger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.ssak3.api.ledger.entity.MyData;

import java.util.List;

public interface MyDataRepository extends JpaRepository<MyData, Long> {

    @Query("SELECT m FROM MyData m WHERE m.theme.themeId = :#{#themeId} AND m.tranYmd LIKE :#{#yearMonth}%")
    List<MyData> findByThemeThemeIdAndTranYmd(Long themeId, String yearMonth);
}
