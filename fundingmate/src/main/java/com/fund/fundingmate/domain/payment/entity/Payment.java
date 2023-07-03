package com.fund.fundingmate.domain.payment.entity;

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
    private Long paymentNo;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    private String paymentType;

    private String paymentCode;

    private String paymentMethod;

    private Integer paymentAmount;
}
