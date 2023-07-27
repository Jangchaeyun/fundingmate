package com.fund.fundingmate.domain.payment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fund.fundingmate.domain.investment.entity.InvestType;
import com.fund.fundingmate.domain.reward.entity.Reward;
import com.fund.fundingmate.domain.reward.entity.RewardType;
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

    private String cardnumber;

    private String cardpassword;

    private Integer paymentamount;

    private String payenddate;

    private String birthday;

    private String payperiod;

    private String shippingadress;

    private String shippingaddressdesc;

    @ManyToOne
    @JoinColumn(name = "reward_type_id")
    @JsonBackReference
    private RewardType rewardType;

    @ManyToOne
    @JoinColumn(name = "invest_type_id")
    @JsonBackReference
    private InvestType investType;
}

