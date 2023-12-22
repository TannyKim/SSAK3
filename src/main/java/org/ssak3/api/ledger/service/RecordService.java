package org.ssak3.api.ledger.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.ssak3.api.category.entity.CustomCategory;
import org.ssak3.api.category.repository.CustomCategoryRepository;
import org.ssak3.api.ledger.dto.request.RecordAddRequest;
import org.ssak3.api.ledger.dto.request.RecordEditRequest;
import org.ssak3.api.ledger.dto.request.RecordListRequest;
import org.ssak3.api.ledger.dto.response.RecordEditResponse;
import org.ssak3.api.ledger.entity.Ledger;
import org.ssak3.api.ledger.entity.Record;
import org.ssak3.api.ledger.repository.LedgerRepository;
import org.ssak3.api.ledger.repository.RecordRepository;
import org.ssak3.api.ledger.repository.mapping.RecordMapping;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class RecordService {

    private final LedgerRepository ledgerRepository;
    private final RecordRepository recordRepository;
    private final CustomCategoryRepository customCategoryRepository;
    private final S3Uploader s3Uploader;

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

        // Check if the CustomCategory is new
        Long ledgerId = recordAddRequest.getLedgerId();
        String categoryName = recordAddRequest.getCategoryName();
        Ledger ledger = ledgerRepository.findByLedgerId(ledgerId);
        CustomCategory reqCategory = new CustomCategory(ledger, categoryName);
        CustomCategory resCategory = customCategoryRepository.findByLedgerLedgerIdAndCustomCategoryCustomCategoryName(reqCategory);
        if (resCategory == null){
            // INSERT new CustomCategory
            resCategory = customCategoryRepository.save(reqCategory);
        }

        // INSERT new Record
        Record record = new Record(ledger, ledger.getTheme(), resCategory);
        record.setCategoryName(categoryName);
        record.setTranName(recordAddRequest.getTranName());
        record.setTranAmount(recordAddRequest.getTranAmount());
        record.setTranYmd(recordAddRequest.getTranYmd());
        record.setTranTime(recordAddRequest.getTranTime());
        record.setTranPlace(recordAddRequest.getTranPlace());
        record.setIsExpense(recordAddRequest.getIsExpense());
        record.setReceiptUrl(recordAddRequest.getReceiptUrl());
        record = recordRepository.save(record);

        // UPDATE monthExpense in the Ledger
        Long monthExpense = ledger.getMonthExpense() == null ? 0 : ledger.getMonthExpense();
        ledger.setMonthExpense(monthExpense + record.getTranAmount());
        ledger = ledgerRepository.save(ledger);

        return record;
    }

    /**
     * 가계부 내역 수정
     * @param recordEditRequest
     * @return
     */
    public RecordEditResponse modifyRecord(RecordEditRequest recordEditRequest) throws IOException {

        // Check if the CustomCategory is new
        Long recordId = recordEditRequest.getRecordId();
        Record record = recordRepository.findByRecordId(recordId);
        Long ledgerId = record.getLedger().getLedgerId();
        Ledger ledger = ledgerRepository.findByLedgerId(ledgerId);

        String categoryName = recordEditRequest.getCategoryName();
        CustomCategory reqCategory = new CustomCategory(ledger, categoryName);
        CustomCategory resCategory = customCategoryRepository.findByLedgerLedgerIdAndCustomCategoryCustomCategoryName(reqCategory);
        if (resCategory == null){
            // INSERT new CustomCategory
            resCategory = customCategoryRepository.save(reqCategory);
        }

        // UPDATE monthExpense in the Ledger
        Long monthExpense = ledger.getMonthExpense() == null ? 0 : ledger.getMonthExpense();
        Integer oldTranAmount = recordRepository.findByRecordId(recordId).getTranAmount();
        Integer newTranAmount = recordEditRequest.getTranAmount();
        ledger.setMonthExpense(monthExpense - oldTranAmount + newTranAmount);
        Ledger updatedLedger = ledgerRepository.save(ledger);

        // UPDATE the Record
        record.setLedger(ledger);
        record.setCustomCategory(resCategory);
        record.setCategoryName(categoryName);
        record.setTranAmount(recordEditRequest.getTranAmount());
        record.setTranName(recordEditRequest.getTranName());
        Record updatedRecord = recordRepository.save(record);

        return new RecordEditResponse(updatedRecord);
    }

    /**
     * 가계부 내역 삭제
     * @param recordId
     */
    public void deleteRecord(Long recordId) {
        recordRepository.deleteByRecordId(recordId);
    }

    public String addRecordReceipt(@Valid Long recordId, MultipartFile image) throws IOException {
        String uploadFileUrl = s3Uploader.upload(image, String.valueOf(recordId));
        Record record = recordRepository.findByRecordId(recordId);
        record.setReceiptUrl(uploadFileUrl);
        recordRepository.save(record);
        return uploadFileUrl;
    }
}
