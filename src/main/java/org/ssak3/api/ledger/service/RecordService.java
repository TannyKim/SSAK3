package org.ssak3.api.ledger.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssak3.api.ledger.dto.request.RecordListRequest;
import org.ssak3.api.ledger.repository.RecordRepository;
import org.ssak3.api.ledger.repository.mapping.RecordMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    public List<RecordMapping> findAllRecordByYearAndMonth(RecordListRequest recordListRequest) {
        List<RecordMapping> monthlyRecordByLedgerIdAndYearAndMonth = recordRepository.findMonthlyRecordByLedgerIdAndYearAndMonth(recordListRequest);
        return monthlyRecordByLedgerIdAndYearAndMonth;
    }
}
