package org.ssak3.api.ledger.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssak3.api.category.entity.CustomCategory;
import org.ssak3.api.category.repository.CustomCategoryRepository;
import org.ssak3.api.ledger.dto.request.RecordEditRequest;
import org.ssak3.api.ledger.dto.request.RecordListRequest;
import org.ssak3.api.ledger.dto.response.RecordEditResponse;
import org.ssak3.api.ledger.entity.Record;
import org.ssak3.api.ledger.repository.RecordRepository;
import org.ssak3.api.ledger.repository.mapping.RecordMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;
    private final CustomCategoryRepository customCategoryRepository;

    public List<RecordMapping> findAllRecordByYearAndMonth(RecordListRequest recordListRequest) {
        List<RecordMapping> monthlyRecordByLedgerIdAndYearAndMonth = recordRepository.findMonthlyRecordByLedgerIdAndYearAndMonth(recordListRequest);
        return monthlyRecordByLedgerIdAndYearAndMonth;
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
