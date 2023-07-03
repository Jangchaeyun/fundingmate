package com.fund.fundingmate.domain.reward.entity;

import com.fund.fundingmate.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reward_comment")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RewardComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer comNo;

    private String comTitle;

    private String commContent;

    private Date comRegistrationDate;

    private Date comRevisionDate;

    @ManyToOne
    @JoinColumn(name = "reward_no")
    private Reward reward;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;
}
