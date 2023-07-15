package com.fund.fundingmate.domain.reward.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fund.fundingmate.domain.reward.entity.Reward;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reward_type")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RewardType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rewardAmount;

    private Boolean rewardAvailableLimit;

    private Integer rewardAvailableCount;

    private String rewardTitle;

    private String rewardContent;


    private Boolean rewardShipAddress;

    @ManyToOne
    @JoinColumn(name = "reward_no")
    @JsonBackReference
    private Reward reward;

    @OneToMany(mappedBy = "rewardType", cascade = CascadeType.ALL)
    private List<RewardOption> rewardOptions;

    public void setRewardOptions(List<RewardOption> rewardOptions) {
        this.rewardOptions = rewardOptions;
        for (RewardOption rewardOption : rewardOptions) {
            rewardOption.setRewardType(this);
        }
    }
}
