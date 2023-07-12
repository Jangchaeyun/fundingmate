package com.fund.fundingmate.domain.reward.entity;

import com.fund.fundingmate.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
    private Long id;

    private String comContent;

    private LocalDate comRegistrationDate;

    private LocalDate comRevisionDate;

    @ManyToOne
    @JoinColumn(name = "reward_no")
    private Reward reward;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RewardReply> replies;
}
