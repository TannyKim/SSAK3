package org.ssak3.api.ledger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ssak3.api.ledger.dto.request.RecordRequest;
import org.ssak3.api.ledger.dto.response.RecordResponse;
import org.ssak3.api.ledger.entity.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

    /**
     * 가계부 생성 - 테마별 결제내역 조회
     *
     * @param recordRequest
     * @return
     */
    @Query("SELECT r FROM Record r WHERE r.ledger.user.userId = :#{#recordRequest.userId} AND r.theme.themeId = :#{#recordRequest.theme.themeId} AND r.tranYmd LIKE :yearMonth%")
    RecordResponse findAllByUserIdAndThemeIdAndYearMonth(RecordRequest recordRequest);
}
