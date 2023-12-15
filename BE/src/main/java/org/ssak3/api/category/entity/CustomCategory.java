package org.ssak3.api.category.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ssak3.api.ledger.entity.Ledger;
import org.ssak3.api.ledger.entity.Theme;
import org.ssak3.api.user.entity.User;

@Entity
@Table(name="CUSTOM_CATEGORY")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CUSTOM_CATEGORY_ID", columnDefinition="BIGINT UNSIGNED")
    private Long customCategoryId;

    @ManyToOne
    @JoinColumn(name = "LEDGER_ID")
    private Ledger ledger;

    @Column(name = "CUSTOM_CATEGORY_NAME")
    private String customCategoryName;

}
