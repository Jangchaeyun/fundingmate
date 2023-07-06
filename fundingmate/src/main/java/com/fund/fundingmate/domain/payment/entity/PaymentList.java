package com.fund.fundingmate.domain.payment.entity;

import com.fund.fundingmate.domain.investment.entity.Investment;
import com.fund.fundingmate.domain.reward.entity.Reward;
import com.fund.fundingmate.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "payment_list")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_list_no")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @ManyToOne
    @JoinColumn(name = "payment_no")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "invest_no")
    private Investment investment;

    @ManyToOne
    @JoinColumn(name = "reward_no")
    private Reward reward;
}