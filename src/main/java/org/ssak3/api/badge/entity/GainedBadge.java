package org.ssak3.api.badge.entity;

import jakarta.persistence.*;
import lombok.*;
import org.ssak3.api.user.entity.User;

@Entity
@Table(name="GAINED_BADGE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GainedBadge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="GAINED_BADGE_ID", columnDefinition="BIGINT UNSIGNED")
    private Long gainedBadgeId; // 획득 배지 아이디

    @ManyToOne
    @JoinColumn(name = "ORIGIN_BADGE_ID")
    private OriginBadge originBadge;

    @Column(name="GAINED_BADGE_NAME", columnDefinition = "VARCHAR(50)")
    private String gainedBadgeName; // 배지명
}
