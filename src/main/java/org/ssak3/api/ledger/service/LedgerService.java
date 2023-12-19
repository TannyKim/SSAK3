package org.ssak3.api.ledger.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssak3.api.category.dto.response.CustomCategoryResponse;
import org.ssak3.api.category.entity.CustomCategory;
import org.ssak3.api.category.entity.OriginCategory;
import org.ssak3.api.category.repository.CustomCategoryRepository;
import org.ssak3.api.category.repository.OriginCategoryRepository;
import org.ssak3.api.ledger.dto.request.LedgerEditRequest;
import org.ssak3.api.ledger.dto.response.PreMonthExpenseResponse;
import org.ssak3.api.ledger.entity.Ledger;
import org.ssak3.api.ledger.entity.MyData;
import org.ssak3.api.ledger.entity.Record;
import org.ssak3.api.ledger.entity.Theme;
import org.ssak3.api.ledger.repository.LedgerRepository;
import org.ssak3.api.ledger.repository.MyDataRepository;
import org.ssak3.api.ledger.repository.RecordRepository;
import org.ssak3.api.ledger.repository.ThemeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class LedgerService {

    private final LedgerRepository ledgerRepository;
    private final RecordRepository recordRepository;
    private final ThemeRepository themeRepository;
    private final CustomCategoryRepository customCategoryRepository;
    private final OriginCategoryRepository originCategoryRepository;
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
    public PreMonthExpenseResponse findPreMonthExpense(long themeId, String yearMonth) {
        List<MyData> myDataList;
        if (themeId == 1) myDataList = myDataRepository.findByTranYmd(yearMonth);
        else myDataList = myDataRepository.findByThemeThemeIdAndTranYmd(themeId, yearMonth);
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
        // Create New Ledger
        Ledger newLedger = ledgerRepository.save(ledger);

        Long themeId = newLedger.getTheme().getThemeId();

        // OriginCategory -> CustomCategory
        List<OriginCategory> originCategoryList = originCategoryRepository.findAllByThemeThemeId(themeId);
        List<CustomCategory> customCategoryList = originCategoryList.stream()
                .map(originCategory -> { return new CustomCategory(newLedger, originCategory.getOriginCategoryName()); })
                .collect(Collectors.toList());
        customCategoryRepository.saveAll(customCategoryList);

        // MyData -> Record
        List<MyData> myDataList;
        if (themeId == 1) {
            myDataList = myDataRepository.findAll();
        } else {
            myDataList = myDataRepository.findAllByThemeThemeId(themeId);
        }
        CustomCategory customCategory = customCategoryRepository.findAllByLedgerLedgerIdOrderByCustomCategoryIdAsc(newLedger.getLedgerId()).get(0);
        List<Record> recordList = myDataList.stream()
                .map(myData -> {
                    Record record = new Record();
                    record.setLedger(newLedger); // 가계부
                    record.setTheme(myData.getTheme()); // 테마
                    record.setCustomCategory(customCategory); // 카테고리
                    record.setCategoryName(customCategory.getCustomCategoryName()); // 카테고리명
                    record.setTranName(myData.getTranPlace()); // 거래명
                    record.setTranAmount(myData.getTranAmount()); // 거래금액
                    record.setTranYmd(myData.getTranYmd()); // 거래년월일
                    record.setTranTime(myData.getTranTime()); // 거래시각
                    record.setTranPlace(myData.getTranPlace()); // 상호명
                    record.setIsExpense(myData.getIsExpense()); // 지출 or 수입
                    return record;
                })
                .collect(Collectors.toList());
        recordRepository.saveAll(recordList);

        return newLedger;
    }

    /**
     * 기존 가계부 편집
     *
     * @param ledgerEditRequest
     * @return
     */
    public Ledger modifyLedger(LedgerEditRequest ledgerEditRequest) {
        Ledger ledger = ledgerRepository.findByLedgerId(ledgerEditRequest.getLedgerId());
        ledger.setGoal(ledgerEditRequest.getGoal());
        ledger.setMonthBudget(ledger.getMonthBudget());
        ledger.setIsPublic(ledger.getIsPublic());
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
