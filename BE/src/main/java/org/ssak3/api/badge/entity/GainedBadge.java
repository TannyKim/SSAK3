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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="GAINED_BADGE_ID", columnDefinition="BIGINT UNSIGNED")
    private Long gainedBadgeId; // 획득 배지 아이디

    @Column(name="BADGE_NAME", columnDefinition = "VARCHAR(50)")
    private String badgeName; // 배지명
}
