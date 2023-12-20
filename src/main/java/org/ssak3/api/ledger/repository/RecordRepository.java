package org.ssak3.api.ledger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ssak3.api.ledger.dto.request.RecordListRequest;
import org.ssak3.api.ledger.dto.request.PreMonthExpenseRequest;
import org.ssak3.api.ledger.entity.Record;
import org.ssak3.api.ledger.repository.mapping.RecordMapping;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

    /**
     * 테마별 결제내역 조회
     *
     * @param recordRequest
     * @return
     */
    @Query("SELECT r FROM Record r WHERE r.ledger.user.userId = :#{#recordRequest.userId} AND r.theme.themeId = :#{#recordRequest.themeId} AND r.tranYmd LIKE :#{#recordRequest.yearMonth}%")
    List<Record> findAllByUserIdAndThemeIdAndYearMonth(PreMonthExpenseRequest recordRequest);

    @Query("SELECT r FROM Record r WHERE r.ledger.ledgerId = :#{#recordListRequest.ledgerId} ORDER BY  r.tranYmd, r.tranTime")
    List<RecordMapping> findMonthlyRecordByLedgerIdAndYearAndMonth(RecordListRequest recordListRequest);


    Record findByRecordId(Long recordId);

    void deleteByRecordId(Long recordId);
}
