package org.ssak3.api.ledger.entity;

import jakarta.persistence.*;
import lombok.*;
import org.ssak3.api.category.entity.CustomCategory;

@Entity
@Table(name="THEME")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="THEME_ID", columnDefinition="BIGINT UNSIGNED")
    private Long themeId;

    @Column(name = "THEME_NAME")
    private String themeName;

    @Builder
    public Theme(
            Long themeId) {
        this.themeId = themeId;
    }

}
