package com.fund.fundingmate.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;

    private String id;

    private String password;

    private String email;

    private String name;

    private String tel;

    private String notificationStatus;

    private Integer vitalization;

    @Override
    public String toString() {
        return "User{" +
                "userNo=" + userNo +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", notificationStatus='" + notificationStatus + '\'' +
                ", vitalization=" + vitalization +
                '}';
    }
}
