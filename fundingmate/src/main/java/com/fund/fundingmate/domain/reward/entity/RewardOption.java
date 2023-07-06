package com.fund.fundingmate.domain.reward.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "reward_option")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RewardOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rewardOptName;

    private String getRewardOptCon;

    @ManyToOne
    @JoinColumn(name = "reward_type_no")
    private RewardType rewardType;
}
