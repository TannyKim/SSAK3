package org.ssak3.api.category.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.ssak3.api.ledger.entity.Ledger;

@Entity
@Table(name="CUSTOM_CATEGORY")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CUSTOM_CATEGORY_ID", columnDefinition="BIGINT UNSIGNED")
    private Long customCategoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LEDGER_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ledger ledger;

    @Column(name = "CUSTOM_CATEGORY_NAME")
    private String customCategoryName;

    @Builder
    public CustomCategory(
            Ledger ledger,
            String customCategoryName
    ) {
        this.ledger = ledger;
        this.customCategoryName = customCategoryName;
    }

}

