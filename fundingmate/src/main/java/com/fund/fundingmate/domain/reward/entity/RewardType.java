package com.fund.fundingmate.domain.reward.entity;

import com.fund.fundingmate.domain.reward.entity.Reward;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reward_type")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class    RewardType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rewardAmount;

    private Boolean rewardAvailableLimit;

    private Integer rewardAvailableCount;

    private String rewardTitle;

    private String rewardContent;

    private String rewardDeliveryDate;

    private Boolean rewardShipAddress;

    @ManyToOne
    @JoinColumn(name = "reward_no")
    private Reward reward;

    @OneToOne(mappedBy = "rewardType", cascade = CascadeType.ALL)
    private RewardOption rewardOption;

    public void setRewardOption(RewardOption rewardOption) {
        this.rewardOption = rewardOption;
        rewardOption.setRewardType(this);
    }
}
