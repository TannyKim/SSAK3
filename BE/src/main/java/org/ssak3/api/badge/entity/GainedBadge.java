package org.ssak3.api.badge.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="GAINED_BADGE")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class GainedBadge {
    @Id
    private Long gainedBadgeId;
    private String badgeName;
}
