package org.ssak3.api.badge.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class GainedBadge {
    private int gainedBadgeId;
    private String badgeName;
}
