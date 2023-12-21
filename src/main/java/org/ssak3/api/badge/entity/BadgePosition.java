package org.ssak3.api.badge.entity;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BadgePosition {

    private Long right;
    private Long left;
    private Long top;
    private Long bottom;
}
