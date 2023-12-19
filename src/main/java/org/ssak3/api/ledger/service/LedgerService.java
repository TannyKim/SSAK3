package org.ssak3.api.ledger.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssak3.api.ledger.dto.response.PreMonthExpenseResponse;
import org.ssak3.api.ledger.entity.Ledger;
import org.ssak3.api.ledger.entity.MyData;
import org.ssak3.api.ledger.entity.Theme;
import org.ssak3.api.ledger.repository.LedgerRepository;
import org.ssak3.api.ledger.repository.MyDataRepository;
import org.ssak3.api.ledger.repository.RecordRepository;
import org.ssak3.api.ledger.repository.ThemeRepository;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class LedgerService {

    private final LedgerRepository ledgerRepository;
    private final RecordRepository recordRepository;
    private final ThemeRepository themeRepository;
    private final MyDataRepository myDataRepository;

    /**
     * 테마 목록 조회
     *
     * @return
     */
    public List<Theme> findThemeList() {
        return themeRepository.findAll();
    }

    /**
     * 테마별 결제내역 조회
     *
     * @param themeId
     * @return
     */
    public PreMonthExpenseResponse findPreMonthExpense(Long themeId, String yearMonth) {
        List<MyData> myDataList = myDataRepository.findByThemeThemeIdAndTranYmd(themeId, yearMonth);
        int expense = 0;
        for (MyData myData : myDataList) {
            expense += myData.getTranAmount();
        }
        PreMonthExpenseResponse preMonthExpenseResponse = new PreMonthExpenseResponse(themeId, expense);
        return preMonthExpenseResponse;
//        return recordRepository.findAllByUserIdAndThemeIdAndYearMonth(recordRequest);
    }

    /**
     * 새로운 가계부 생성
     *
     * @param ledger
     * @return
     */
    public Ledger addLedger(Ledger ledger) {
        return ledgerRepository.save(ledger);
    }

    /**
     * 기존 가계부 편집
     *
     * @param ledger
     * @return
     */
    public Ledger modifyLedger(Ledger ledger) {
        return ledgerRepository.save(ledger);
    }

    /**
     * 기존 가계부 삭제
     *
     * @param ledgerId
     * @return
     */
    @Transactional
    public void removeLedger(Long ledgerId) {
        ledgerRepository.deleteById(ledgerId);
    }

    /**
     * 나의 가계부 목록 조회
     *
     * @return
     */
    public List<Ledger> findMyLedgerList(Long userId) { return ledgerRepository.findAllByUserUserId(userId); }

    /**
     * 나를 제외한 모든 사용자의 가계부 목록 조회
     *
     * @return
     */
    public List<Ledger> findOthersLedgerList(Long userId) {
        return ledgerRepository.findOthersLedgerList(userId);
    }

}
