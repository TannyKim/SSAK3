package org.ssak3.api.badge.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="ORIGIN_BADGE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OriginBadge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ORIGIN_BADGE_ID", columnDefinition="BIGINT UNSIGNED")
    private Long originBadgeId; // 배지 아이디

    @Column(name="ORIGIN_BADGE_NAME", columnDefinition = "VARCHAR(50)")
    private String badgeName; // 배지명
}
