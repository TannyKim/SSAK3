package org.ssak3.api.ledger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.ssak3.api.ledger.entity.Ledger;

import java.util.List;

@Repository
public interface LedgerRepository extends JpaRepository<Ledger, Long> {

    /**
     * 나를 제외한 모든 사용자의 가계부 목록 조회
     * @param userId
     * @return
     */
    @Query("SELECT l FROM Ledger l WHERE l.user.userId != :userId")
    List<Ledger> findOthersLedgerList(Long userId);

    /**
     * 나의 가계부 목록 조회
     * @param userId
     * @return
     */
    List<Ledger> findAllByUserUserId(Long userId);

    Ledger findByLedgerId(Long ledgerId);

    /**
     * 매달 1일 00시 00분 00초에 모든 Ledger의 monthExpense를 0으로 초기화
     */
    @Modifying
    @Query("UPDATE Ledger l SET l.monthExpense = 0")
    void initMonthExpense();
}
