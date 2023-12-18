package org.ssak3.api.ledger.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.ssak3.api.ledger.entity.Ledger;
import org.ssak3.api.ledger.entity.Theme;

import java.util.List;

@Data
@NoArgsConstructor
public class LedgerResponse {

    @Schema(description = "ledger list")
    List<Ledger> ledgerList;

    @Schema(description = "theme list")
    List<Theme> themeList;
}
