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
import org.ssak3.api.ledger.dto.request.RecordRequest;
import org.ssak3.api.ledger.entity.Ledger;
import org.ssak3.api.ledger.entity.Record;
import org.ssak3.api.ledger.entity.Theme;
import org.ssak3.api.ledger.repository.LedgerRepository;
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
     * @param recordRequest
     * @return
     */
    public List<Record> findRecordList(RecordRequest recordRequest) {
        return recordRepository.findAllByUserIdAndThemeIdAndYearMonth(recordRequest);
    }

    /**
     * 새로운 가계부 생성
     *
     * @param ledger
     * @return
     */
    public Ledger addLedger(Ledger ledger) {
        Ledger newLedger = ledgerRepository.save(ledger);
        List<OriginCategory> originCategoryList = originCategoryRepository.findAllByThemeThemeId(newLedger.getTheme().getThemeId());
        List<CustomCategory> customCategoryList = originCategoryList.stream()
                .map(originCategory -> { return new CustomCategory(newLedger, originCategory.getOriginCategoryName()); })
                .collect(Collectors.toList());
        customCategoryRepository.saveAll(customCategoryList);
        return newLedger;
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
