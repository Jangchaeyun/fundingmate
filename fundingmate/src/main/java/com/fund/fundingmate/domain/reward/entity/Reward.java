package com.fund.fundingmate.domain.reward.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fund.fundingmate.domain.user.entity.User;
import com.fund.fundingmate.global.file.entity.File;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Reward")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projName;

    private Integer projTargetAmount;

    private LocalDate projDateStart;

    private LocalDate projDateEnd;

    private LocalDate deliveryDate;

    @ManyToOne
    @JoinColumn(name="rep_img")
    private File repfile;

    private String projKeyWord;

    private String rewardVideoAddress;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="con_img")
    private File conFile;
    
    @Column(columnDefinition = "VARCHAR(1000)")
    private String projContent;

    @Column(columnDefinition = "VARCHAR(1000)")
    private String rewardRefundExchangePolicy;

    private String rewardContact;

    private String rewardEmail;

    private String rewardCategory;

    private String modelName;

    private String countryOfOrigin;

    private String manufacturer;

    @Column(columnDefinition = "VARCHAR(1000)")
    private String rewardLaw;

    private String asPhoneNumber;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "business_img")
    private File businessImg;

    private String businessAddress;

    private String bank;

    private String accNumber;

    private String depositorName;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "bank_img")
    private File bankImg;

    @Column(nullable = true)
    private String taxBillEmail;

    @Column(nullable = true)
    private String websiteUrl;

    @Column(nullable = true)
    private String facebookUrl;

    @Column(nullable = true)
    private String instagramUrl;

    @Column(nullable = true)
    private String blogUrl;

    @Column(nullable = true)
    private String twitterUrl;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @OneToMany(mappedBy = "reward", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<RewardType> rewardTypes;
}