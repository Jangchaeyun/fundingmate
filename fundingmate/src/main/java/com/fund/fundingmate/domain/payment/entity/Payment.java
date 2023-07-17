package com.fund.fundingmate.domain.payment.entity;

import com.fund.fundingmate.domain.reward.entity.Reward;
import com.fund.fundingmate.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "payment")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @ManyToOne
    @JoinColumn(name = "reward_no")
    private Reward reward;

    private String cardnumber;

    private String cardpassword;

    private Integer paymentamount;

    private String payenddate;

    private String birthday;

    private String payperiod;

    private String shippingadress;

    private String shippingaddressdesc;
}

