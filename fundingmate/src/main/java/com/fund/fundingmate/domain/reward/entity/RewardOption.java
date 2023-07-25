package com.fund.fundingmate.domain.reward.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "reward_option")
@Getter
@Setter
@ToString(exclude = {"rewardType"})
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "rewardType" })
public class RewardOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rewardOptName;

    private String getRewardOptCon;

    @ManyToOne
    @JoinColumn(name = "reward_type_id")
    private RewardType rewardType;

//    @OneToMany(mappedBy = "rewardOption")
//    private List<RewardType> rewardTypes;
}
