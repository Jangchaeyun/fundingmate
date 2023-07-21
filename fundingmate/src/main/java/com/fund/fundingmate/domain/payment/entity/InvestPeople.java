package com.fund.fundingmate.domain.payment.entity;

import com.fund.fundingmate.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "invest_people")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InvestPeople {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_no")
    private User user;

    private String name;

    private String securitynumber1;

    private String securitynumber2;

    private String calltype;

    private String callnumber;
}
