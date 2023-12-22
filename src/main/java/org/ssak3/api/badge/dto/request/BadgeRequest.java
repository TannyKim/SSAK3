package org.ssak3.api.badge.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BadgeRequest {
    @Schema(description = "badge id", example = "1")
    Long badgeId; // 배지 아이디

    @Schema(description = "is the badge fixed", example = "0 or 1")
    String isFixed; // 배지 부착 여부
}
