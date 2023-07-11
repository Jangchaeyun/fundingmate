package com.fund.fundingmate.domain.investment.entity;

import com.fund.fundingmate.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

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
    private Long investNo;

    private String investCategory;

    private String investProjName;

    private Integer investTargetAmount;

    private Date investProjDateStart;

    private Date investProjDateEnd;

    private String investRepImgSavedName;

    private String investProjKeyword;

    private String useOfFunds;

    private Date useOfFundsDateStart;

    private Date useOfFundsDateEnd;

    private Integer rateOfReturn;

    private Date expectedPaymentDate;

    private String repaymentMethod;

    private String investVideoUrl;

    private String investContentImgSavedName;

    private String investItemIntro;

    private String investItemBusinessValue;

    private String investItemValue;

    private String investItemBenefit;

    private String investProjContent;

    private String investIdBusinessLicenseImgSavedName;

    private String businessAddress;

    private String investEmail;

    private String bank;

    private String accNumber;

    private String depositorName;

    private String investBankAccountCopyImgSavedName;

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


   /* @Builder
    public Investment(Long investNo, String investCategory, String investProjName, Integer investTargetAmount, Date investProjDateStart, Date investProjDateEnd,
                      String investRepImgSavedName, String investProjKeyword,String useOfFunds, Date useOfFundsDateStart, Date useOfFundsDateEnd,
                      Integer rateOfReturn,Date expectedPaymentDate,  String repaymentMethod,String investVideoUrl, String investContentImgSavedName,
                      String investItemIntro, String investItemBusinessValue, String investItemValue, String investItemBenefit,
                      String investProjContent, String investIdBusinessLicenseImgSavedName,String taxBillEmail,
                      String websiteUrl, String facebookUrl,  String instagramUrl, String blogUrl,String twitterUrl , Integer userNo,User user ){
        this.investNo = investNo;
        this.investCategory = investCategory;
        this.investProjName = investProjName;
        this.investTargetAmount = investTargetAmount;
        this.investProjDateStart = investProjDateStart;
        this.investProjDateEnd = investProjDateEnd;
        this.investRepImgSavedName = investRepImgSavedName;
        this.investProjKeyword = investProjKeyword;
        this.useOfFunds = useOfFunds;
        this.useOfFundsDateStart = useOfFundsDateStart;
        this.useOfFundsDateEnd = useOfFundsDateEnd;
        this.rateOfReturn = rateOfReturn;
        this.expectedPaymentDate = expectedPaymentDate;
        this.repaymentMethod = repaymentMethod;
        this.investVideoUrl = investVideoUrl;
        this.investContentImgSavedName = investContentImgSavedName;
        this.investItemIntro = investItemIntro;
        this.investItemBusinessValue = investItemBusinessValue;
        this.investItemValue = investItemValue;
        this.investItemBenefit = investItemBenefit;
        this.investProjContent = investProjContent;
        this.investIdBusinessLicenseImgSavedName = investIdBusinessLicenseImgSavedName;
        this.taxBillEmail = taxBillEmail;
        this.websiteUrl = websiteUrl;
        this.facebookUrl = facebookUrl;
        this.instagramUrl = instagramUrl;
        this.blogUrl = blogUrl;
        this.twitterUrl = twitterUrl;
        this.userNo = userNo;
        this.user=user;
    }
*/

}
