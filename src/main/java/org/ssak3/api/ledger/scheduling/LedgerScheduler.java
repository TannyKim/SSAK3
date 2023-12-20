package org.ssak3.api.ledger.scheduling;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.ssak3.api.ledger.service.LedgerService;

@Component
@RequiredArgsConstructor
public class LedgerScheduler {

    private final LedgerService ledgerService;
    @Scheduled(cron = "0 0 0 1 * ?") // 매달 1일 00시 00분 00초에 실행
    public void initMonthExpense() {
        ledgerService.initMonthExpense();
    }
}
