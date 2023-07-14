package com.fund.fundingmate.domain.reward.entity;

import com.fund.fundingmate.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
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
    private Long id;

    private String repContent;

    private LocalDate repRegistrationDate;

    private LocalDate repRevisionDate;

    @ManyToOne
    @JoinColumn(name = "reward_no")
    private Reward reward;

    @ManyToOne
    @JoinColumn(name = "comm_no")
    private RewardComment comment;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;
}
