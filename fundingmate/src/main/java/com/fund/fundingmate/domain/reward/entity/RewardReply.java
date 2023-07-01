package com.fund.fundingmate.domain.reward.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reward_reply")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RewardReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long repNo;

    private String repTitle;

    private String repContent;

    private Date repRegistrationDate;

    private Date repRevisionDate;

    @ManyToOne
    @JoinColumn(name = "reward_no")
    private Reward reward;

    @ManyToOne
    @JoinColumn(name = "comm_no")
    private RewardComment comment;
}
