package com.fund.fundingmate.domain.user.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;

    private String id;

    private String password;

    private String email;

    private String name;

    private String birthday;

    private String tel;

    private String notificationStatus;

    private Integer vitalization;
}
