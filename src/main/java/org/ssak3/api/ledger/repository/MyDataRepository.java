package org.ssak3.api.ledger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.ssak3.api.ledger.entity.MyData;

import java.util.List;

@Repository
public interface MyDataRepository extends JpaRepository<MyData, Long> {

    @Query("SELECT m FROM MyData m WHERE m.theme.themeId = :#{#themeId} AND m.tranYmd LIKE :#{#yearMonth}%")
    List<MyData> findByThemeThemeIdAndTranYmd(long themeId, String yearMonth);

    @Query("SELECT m FROM MyData m WHERE m.tranYmd LIKE :#{#yearMonth}%")
    List<MyData> findByTranYmd(String yearMonth);

    /**
     * 테마별 결제내역 조회
     * @param themeId
     * @return
     */
    List<MyData> findAllByThemeThemeId(Long themeId);
}
