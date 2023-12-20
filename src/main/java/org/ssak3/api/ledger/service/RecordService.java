package org.ssak3.api.ledger.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssak3.api.category.entity.CustomCategory;
import org.ssak3.api.category.repository.CustomCategoryRepository;
import org.ssak3.api.ledger.dto.request.RecordAddRequest;
import org.ssak3.api.ledger.dto.request.RecordEditRequest;
import org.ssak3.api.ledger.dto.request.RecordListRequest;
import org.ssak3.api.ledger.dto.response.RecordEditResponse;
import org.ssak3.api.ledger.entity.Ledger;
import org.ssak3.api.ledger.entity.Record;
import org.ssak3.api.ledger.entity.Theme;
import org.ssak3.api.ledger.repository.LedgerRepository;
import org.ssak3.api.ledger.repository.RecordRepository;
import org.ssak3.api.ledger.repository.mapping.RecordMapping;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class RecordService {

    private final LedgerRepository ledgerRepository;
    private final RecordRepository recordRepository;
    private final CustomCategoryRepository customCategoryRepository;

    public List<RecordMapping> findAllRecordByYearAndMonth(RecordListRequest recordListRequest) {
        List<RecordMapping> monthlyRecordByLedgerIdAndYearAndMonth = recordRepository.findMonthlyRecordByLedgerIdAndYearAndMonth(recordListRequest);
        return monthlyRecordByLedgerIdAndYearAndMonth;
    }

    /**
     * 가계부 내역 추가
     * @param recordAddRequest
     * @return
     */
    public Record addRecord(RecordAddRequest recordAddRequest) {

        // Check if the CustomCategory is New
        Long ledgerId = recordAddRequest.getLedgerId();
        String categoryName = recordAddRequest.getCategoryName();
        CustomCategory newCategory = new CustomCategory(new Ledger(ledgerId), categoryName);
        CustomCategory category = customCategoryRepository.findByLedgerLedgerIdAndCustomCategoryCustomCategoryName(newCategory);
        if (category == null){
            // INSERT New CustomCategory
            category = customCategoryRepository.save(newCategory);
        }
        System.out.println(category.toString());

        // INSERT New Record
        Record record = new Record(new Ledger(ledgerId), new Theme(recordAddRequest.getThemeId()), new CustomCategory(category.getCustomCategoryId()));
        record.setCategoryName(category.getCustomCategoryName());
        record.setTranName(recordAddRequest.getTranName());
        record.setTranAmount(recordAddRequest.getTranAmount());
        record.setTranYmd(recordAddRequest.getTranYmd());
        record.setTranTime(recordAddRequest.getTranTime());
        record.setTranPlace(recordAddRequest.getTranPlace());
        record.setIsExpense(recordAddRequest.getIsExpense());
        record.setReceiptUrl(recordAddRequest.getReceiptUrl());
        record = recordRepository.save(record);

        // UPDATE MonthExpense in the Ledger
        Ledger ledger = ledgerRepository.findByLedgerId(ledgerId);
        Long monthExpense = ledger.getMonthExpense() == null ? 0 : ledger.getMonthExpense();
        ledger.setMonthExpense(monthExpense + record.getTranAmount());
        ledger = ledgerRepository.save(ledger);

        return record;
    }

    public RecordEditResponse modifyRecord(RecordEditRequest recordEditRequest) {
        Long recordId = recordEditRequest.getRecordId();
        CustomCategory customCategory = customCategoryRepository.findByCustomCategoryId(recordEditRequest.getCategoryId());
        Record currRecord = recordRepository.findByRecordId(recordId);

        currRecord.setCustomCategory(customCategory);
        currRecord.setCategoryName(recordEditRequest.getCategoryName());
        currRecord.setTranAmount(recordEditRequest.getTranAmount());
        currRecord.setTranName(recordEditRequest.getTranName());

        Record save = recordRepository.save(currRecord);
        return new RecordEditResponse(save);
    }

    @Transactional
    public void deleteRecord(Long recordId) {
        recordRepository.deleteByRecordId(recordId);
    }

}
