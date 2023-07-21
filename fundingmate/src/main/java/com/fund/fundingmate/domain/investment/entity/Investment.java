package com.fund.fundingmate.domain.investment.entity;

import com.fund.fundingmate.domain.reward.entity.RewardType;
import com.fund.fundingmate.domain.user.entity.User;
import com.fund.fundingmate.global.file.dto.FileDTO;
import com.fund.fundingmate.global.file.entity.File;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "investment")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String investCategory;

    private String investProjName;

    private Integer investTargetAmount;

    private Date investProjDateStart;

    private Date investProjDateEnd;
    private Long investRepImgSavedName;

    private String investProjKeyword;

    private String useOfFunds;

    private Date useOfFundsDateStart;

    private Date useOfFundsDateEnd;

    private Integer rateOfReturn;

    private Date expectedPaymentDate;

    private String repaymentMethod;

    private String investVideoUrl;

  /*  @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name="investContentImgSavedName_img")
    private List<File> investContentImgSavedName;*/


    private String  investContentImgSavedName;  //파일번호 목록: 1,2,3

    private String investItemIntro;

    private String investItemBusinessValue;

    private String investItemValue;

    private String investItemBenefit;

    private String investProjContent;

    private Long investIdBusinessLicenseImgSavedName;

    private String businessAddress;

    private String investEmail;

    private String bank;

    private String accNumber;

    private String depositorName;

    private Long investBankAccountCopyImgSavedName;

    private String taxBillEmail;

    @Column(nullable = false)
    private String websiteUrl;

    @Column(nullable = false)
    private String facebookUrl;

    @Column(nullable = false)
    private String instagramUrl;

    @Column(nullable = false)
    private String blogUrl;

    @Column(nullable = false)
    private String twitterUrl;
/*        @Column(name = "user_no")
        private Integer userNo;*/
    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @OneToMany(mappedBy = "investment", cascade = CascadeType.ALL)
    private List<InvestType> investTypes = new ArrayList<>();

}
