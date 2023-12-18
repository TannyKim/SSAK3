package org.ssak3.api.ledger.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.ssak3.api.ledger.repository.mapping.RecordMapping;

import java.util.List;

@Data
@NoArgsConstructor
public class RecordListResponse {

    List<RecordMapping> recordList;

    public RecordListResponse(List<RecordMapping> recordList) {
        this.recordList = recordList;
    }

}
