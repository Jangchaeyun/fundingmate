package com.fund.fundingmate.domain.reward.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fund.fundingmate.domain.payment.entity.Payment;
import com.fund.fundingmate.domain.reward.entity.Reward;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "reward_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"payments", "reward"})
public class RewardType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rewardAmount;

    private Boolean rewardAvailableLimit;

    private Integer rewardAvailableCount;

    private String rewardTitle;

    private String rewardContent;

    private Date deliveryDate;

    private Boolean rewardShipAddress;

    @ManyToOne
    @JoinColumn(name = "reward_id")
    private Reward reward;

    @OneToMany(mappedBy = "rewardType", cascade = CascadeType.ALL)
    private List<RewardOption> options = new ArrayList<>();

    @OneToMany(mappedBy = "rewardType", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Payment> payments = new HashSet<>();
}
