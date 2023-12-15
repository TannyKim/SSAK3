package org.ssak3.api.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="USER")
@Getter
@ToString
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

}
