package org.ssak3.api.ledger.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssak3.api.ledger.entity.Ledger;
import org.ssak3.api.ledger.repository.LedgerRepository;
import org.ssak3.api.ledger.repository.RecordRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LedgerService {

    private final LedgerRepository ledgerRepository;
    private final RecordRepository recordRepository;

    /**
     * 나를 제외한 모든 사용자의 가계부 목록 조회
     * @return
     */
    public List<Ledger> findOthersLedgerList(Long userId){
        return ledgerRepository.findOthersLedgerList(userId);
    }

    /**
     * 나의 가계부 목록 조회
     * @return
     */
    public List<Ledger> findMyLedgerList(Long userId) {
        return ledgerRepository.findAllByUserUserId(userId);
    }

    /**
     * 새로운 가계부 생성
     * @param ledger
     * @return
     */
    public Ledger addLedger(Ledger ledger) {
        return ledgerRepository.save(ledger);
    }

    /**
     * 기존 가계부 편집
     * @param ledger
     * @return
     */
    public Ledger modifyLedger(Ledger ledger) {
        return ledgerRepository.save(ledger);
    }

    /**
     * 기존 가계부 삭제
     * @param ledgerId
     * @return
     */
    public void removeLedger(Long ledgerId) {
        ledgerRepository.deleteById(ledgerId);
    }
}
