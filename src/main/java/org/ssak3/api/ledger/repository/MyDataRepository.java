package org.ssak3.api.ledger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssak3.api.ledger.entity.MyData;

import java.util.List;

@Repository
public interface MyDataRepository extends JpaRepository<MyData, Long> {

    /**
     * 테마별 결제내역 조회
     * @param themeId
     * @return
     */
    List<MyData> findAllByThemeThemeId(Long themeId);
}
