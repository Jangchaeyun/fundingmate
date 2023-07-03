package com.fund.fundingmate.domain.reward.entity;

import com.fund.fundingmate.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Reward")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rewardNo;

    private String projName;

    private Integer projTargetAmout;

    private Date projDateStart;

    private Date projDateEnd;

    private String rewardRepImgSavedName;

    private String projKeyWord;

    private String rewardVideoAddress;

    private String rewardContentImgSavedName;

    private String projContent;

    private String rewardRefundExchangePolicy;

    private String rewardContact;

    private String rewardEmail;

    private String rewardCategory;

    private String modelName;

    private String countryOfOrigin;

    private String manufacturer;

    private String asPhoneNumber;

    private String rewardIdBusinessLicenseImgSavedName;

    private String businessAddress;

    private String bank;

    private String accNumber;

    private String depositorName;

    private String rewardBankAccountCopyImgSavedName;

    @Column(nullable = false)
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

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @Override
    public String toString() {
        return "Reward{" +
                "rewardNo=" + rewardNo +
                ", projName='" + projName + '\'' +
                ", projTargetAmout=" + projTargetAmout +
                ", projDateStart=" + projDateStart +
                ", projDateEnd=" + projDateEnd +
                ", rewardRepImgSavedName='" + rewardRepImgSavedName + '\'' +
                ", projKeyWord='" + projKeyWord + '\'' +
                ", rewardVideoAddress='" + rewardVideoAddress + '\'' +
                ", rewardContentImgSavedName='" + rewardContentImgSavedName + '\'' +
                ", projContent='" + projContent + '\'' +
                ", rewardRefundExchangePolicy='" + rewardRefundExchangePolicy + '\'' +
                ", rewardContact='" + rewardContact + '\'' +
                ", rewardEmail='" + rewardEmail + '\'' +
                ", rewardCategory='" + rewardCategory + '\'' +
                ", modelName='" + modelName + '\'' +
                ", countryOfOrigin='" + countryOfOrigin + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", asPhoneNumber='" + asPhoneNumber + '\'' +
                ", rewardIdBusinessLicenseImgSavedName='" + rewardIdBusinessLicenseImgSavedName + '\'' +
                ", businessAddress='" + businessAddress + '\'' +
                ", bank='" + bank + '\'' +
                ", accNumber='" + accNumber + '\'' +
                ", depositorName='" + depositorName + '\'' +
                ", rewardBankAccountCopyImgSavedName='" + rewardBankAccountCopyImgSavedName + '\'' +
                ", taxBillEmail='" + taxBillEmail + '\'' +
                ", websiteUrl='" + websiteUrl + '\'' +
                ", facebookUrl='" + facebookUrl + '\'' +
                ", instagramUrl='" + instagramUrl + '\'' +
                ", blogUrl='" + blogUrl + '\'' +
                ", twitterUrl='" + twitterUrl + '\'' +
                ", user=" + user +
                '}';
    }
}
