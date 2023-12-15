package org.ssak3.api.badge.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@RequiredArgsConstructor
@Getter
@Setter
public class GainedBadge {

    private int gainedBadgeId;
    private String badgeName;
}
