package org.ssak3.api.badge.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
public class BadgePosition {

    @Column(name = "RIGHT_POSITION", columnDefinition = "BIGINT")
    private Long right;

    @Column(name = "LEFT_POSITION", columnDefinition = "BIGINT")
    private Long left;

    @Column(name = "TOP_POSITION", columnDefinition = "BIGINT")
    private Long top;

    @Column(name = "BOTTOM_POSITION", columnDefinition = "BIGINT")
    private Long bottom;
}
