package org.ssak3.api.ledger.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="RECORD")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="RECORD_ID", columnDefinition="BIGINT UNSIGNED")
    private Long recordId; // 레코드 아이디

    @Column(name="CATEGORY_NAME", columnDefinition="VARCHAR(50)")
    private String categoryName; // 카테고리명

    @Column(name="TRAN_NAME", columnDefinition="VARCHAR(50)")
    private String tranName; // 거래명

    @Column(name="TRAN_YMD", columnDefinition="VARCHAR(50)")
    private String tranYmd; // 거래년월일

    @Column(name="TRAN_TIME", columnDefinition="VARCHAR(50)")
    private String tranTime; // 거래시각

    @Column(name="TRAN_PLACE", columnDefinition="VARCHAR(50)")
    private String tranPlace; // 상호명
}
