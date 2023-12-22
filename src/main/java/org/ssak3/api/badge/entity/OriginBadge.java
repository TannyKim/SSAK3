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

    @Column(name="IS_GAINED", columnDefinition = "VARCHAR(1)")
    private String isGained; // 배지 획득 여부

    @Column(name="IS_FIXED", columnDefinition = "VARCHAR(1)")
    private String isFixed; // 배지 부착 여부

//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "right", column = @Column(name = "RIGHT_POSITION")),
//            @AttributeOverride(name = "left", column = @Column(name = "LEFT_POSITION")),
//            @AttributeOverride(name = "top", column = @Column(name = "TOP_POSITION")),
//            @AttributeOverride(name = "bottom", column = @Column(name = "BOTTOM_POSITION"))
//    })
//    private BadgePosition badgePosition; // 배지위치
}
