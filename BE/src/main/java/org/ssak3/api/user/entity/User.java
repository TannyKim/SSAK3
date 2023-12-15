package org.ssak3.api.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="USER")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="USER_ID", columnDefinition="BIGINT UNSIGNED")
    private Long userId;

    @Column(name="USER_NAME", columnDefinition="VARCHAR(20)")
    private String userName;

    @Column(name="AGE", columnDefinition="INT")
    private Integer age;

    @Column(name="INCOME", columnDefinition="BIGINT UNSIGNED")
    private Long income;

}
