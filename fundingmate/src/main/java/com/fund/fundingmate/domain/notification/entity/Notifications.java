package com.fund.fundingmate.domain.notification.entity;

import com.fund.fundingmate.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Notifications")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationNo;

    private String notificationRewards;

    private String notificationFunded;

    private String notificationFavorites;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;
}
