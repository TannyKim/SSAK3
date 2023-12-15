package org.ssak3.api.badge.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="ORIGIN_BADGE")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class OriginBadge {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ORIGIN_BADGE_ID", columnDefinition="INT UNSIGNED")
    private Long originBadgeId;

    @Column(name="BADGE_NAME")
    private String badgeName;
}
