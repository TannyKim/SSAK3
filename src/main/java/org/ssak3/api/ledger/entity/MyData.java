package org.ssak3.api.ledger.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MYDATA")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MYDATA_ID", columnDefinition = "BIGINT UNSIGNED")
    private Long myDataId; // 마이데이터 아이디

    @ManyToOne
    @JoinColumn(name = "THEME_ID")
    private Theme theme;

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
}
