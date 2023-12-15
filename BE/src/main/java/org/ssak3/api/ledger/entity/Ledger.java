package org.ssak3.api.ledger.entity;

import jakarta.persistence.*;
import lombok.*;
import org.ssak3.api.user.entity.User;


@Entity
@Table(name="LEDGER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ledger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="LEDGER_ID", columnDefinition="BIGINT UNSIGNED")
    private Long ledgerId; // 가계부 아이디

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne
    @JoinColumn(name = "THEME_ID")
    private Theme theme;

    @Column(name="GOAL", columnDefinition="VARCHAR(100)")
    private String goal; // 목표 (가계부 제목)

    @Column(name="MONTH_BUDGET", columnDefinition="BIGINT UNSIGNED")
    private Long monthBudget; // 월 예산

    @Column(name="MONTH_EXPENSE", columnDefinition="BIGINT UNSIGNED")
    private Long monthExpense; // 월 지출

    @Column(name="IS_PUBLIC", columnDefinition="CHAR(1)")
    private String isPublic; // 가계부 공개여부

}
