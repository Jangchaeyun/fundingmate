package com.fund.fundingmate.domain.reward.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    private Long rewardTypeId;

//    @OneToMany(mappedBy = "rewardOption")
//    private List<RewardType> rewardTypes;
}
