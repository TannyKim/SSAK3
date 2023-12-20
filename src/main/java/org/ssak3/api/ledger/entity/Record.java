package org.ssak3.api.ledger.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.ssak3.api.category.entity.CustomCategory;

@Entity
@Table(name = "RECORD")
@Getter
@Setter
@NoArgsConstructor
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECORD_ID", columnDefinition = "BIGINT UNSIGNED")
    private Long recordId; // 레코드 아이디

    @ManyToOne
    @JoinColumn(name = "LEDGER_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ledger ledger;

    @ManyToOne
    @JoinColumn(name = "THEME_ID")
    private Theme theme;

    @ManyToOne
    @JoinColumn(name = "CUSTOM_CATEGORY_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CustomCategory customCategory;

    @Column(name = "CATEGORY_NAME", columnDefinition = "VARCHAR(50)")
    private String categoryName; // 카테고리명

    @Column(name = "TRAN_NAME", columnDefinition = "VARCHAR(50)")
    private String tranName; // 거래명

    @Column(name = "TRAN_AMOUNT", columnDefinition = "INT UNSIGNED")
    private Integer tranAmount; // 거래금액

    @Column(name = "TRAN_YMD", columnDefinition = "VARCHAR(50)")
    private String tranYmd; // 거래년월일

    @Column(name = "TRAN_TIME", columnDefinition = "VARCHAR(50)")
    private String tranTime; // 거래시각

    @Column(name = "TRAN_PLACE", columnDefinition = "VARCHAR(50)")
    private String tranPlace; // 상호명

    @Column(name = "IS_EXPENSE", columnDefinition = "VARCHAR(1)")
    private String isExpense; // 지출 or 수입

    @Column(name = "RECEIPT_URL", columnDefinition = "VARCHAR(1000)")
    private String receiptUrl; // 영수증 URL

    @Builder
    public Record(
            Ledger ledger,
            Theme theme,
            CustomCategory customCategory) {
        this.ledger = ledger;
        this.theme = theme;
        this.customCategory = customCategory;
    }
}
