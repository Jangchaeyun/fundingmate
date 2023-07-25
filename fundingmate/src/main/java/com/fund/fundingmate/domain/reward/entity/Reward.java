package com.fund.fundingmate.domain.reward.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fund.fundingmate.domain.user.entity.User;
import com.fund.fundingmate.global.file.entity.File;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

@Entity
@Table(name = "Reward")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(value = { "rewardTypes" })
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projName;

    private Integer projTargetAmount;

    private Date projDateStart;

    private Date projDateEnd;

    private Long rewardRepImgSavedName;

    private String projKeyWord;

    private String rewardVideoAddress;

    private String rewardContentImgSavedName; //파일번호 목록: 1,2,3

    @Column(length = 100000)
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

    private Long rewardIdBusinessLicenseImgSavedName;

    private String businessAddress;

    private String bank;

    private String accNumber;

    private String depositorName;

    private Long rewardBankAccountCopyImgSavedName;

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
    private List<RewardType> rewardTypes = new ArrayList<>();;
}