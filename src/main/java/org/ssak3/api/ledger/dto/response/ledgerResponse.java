package org.ssak3.api.ledger.dto.response;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.ssak3.api.ledger.entity.Ledger;

import java.util.List;

@ToString
@RequiredArgsConstructor
public class ledgerResponse {
    List<Ledger> ledgerList;
    List<String> titles;
}
