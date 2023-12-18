package org.ssak3.api.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="USER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USER_ID")
    private Long userId;

    @Column(name="KB_PIN")
    private String kbPIN;

    @Column(name="USER_NAME", columnDefinition="VARCHAR(20)")
    private String userName;

    @Column(name="AGE", columnDefinition="INT")
    private Integer age;

    @Column(name="INCOME", columnDefinition="BIGINT UNSIGNED")
    private Long income;

    @Builder
    public User(
            String kbPIN,
            String userName,
            int age,
            long income
    ){
        this.kbPIN = kbPIN;
        this.userName = userName;
        this.age = age;
        this.income = income;
    }

}
