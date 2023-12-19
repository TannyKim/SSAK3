package org.ssak3.api.category.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ssak3.api.ledger.entity.Theme;

@Entity
@Table(name="ORIGIN_CATEGORY")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OriginCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ORIGIN_CATEGORY_ID", columnDefinition="BIGINT UNSIGNED")
    private Long originCategoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "THEME_ID")
    private Theme theme;

    @Column(name = "ORIGIN_CATEGORY_NAME", columnDefinition = "VARCHAR(100)")
    private String originCategoryName;
}
