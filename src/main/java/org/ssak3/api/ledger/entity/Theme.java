package org.ssak3.api.ledger.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="THEME")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="THEME_ID", columnDefinition="BIGINT UNSIGNED")
    private Long themeId;

    @Column(name = "THEME_NAME")
    private String themeName;

}
