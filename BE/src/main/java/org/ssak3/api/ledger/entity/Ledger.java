package org.ssak3.api.ledger.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="LEDGER")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Ledger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="LEDGER_ID", columnDefinition="BIGINT UNSIGNED")
    private Long ledgerId; // 가계부 아이디

    @Column(name="USER_ID", columnDefinition="BIGINT UNSIGNED")
    private Long userId;

    @Column(name="THEME_ID", columnDefinition="BIGINT UNSIGNED")
    private Long themeId;

    @Column(name="GOAL", columnDefinition="VARCHAR(100)")
    private String goal; // 목표 (가계부 제목)

    @Column(name="MONTH_BUDGET", columnDefinition="BIGINT UNSIGNED")
    private Long monthBudget; // 월 예산

    @Column(name="MONTH_EXPENSE", columnDefinition="BIGINT UNSIGNED")
    private Long monthExpense; // 월 지출

    @Column(name="IS_PUBLIC", columnDefinition="CHAR(1)")
    private String isPublic; // 가계부 공개여부

}
