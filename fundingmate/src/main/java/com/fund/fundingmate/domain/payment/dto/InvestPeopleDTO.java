package com.fund.fundingmate.domain.payment.dto;

import com.fund.fundingmate.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvestPeopleDTO {
    private Long id;
    private User user;
    private String name;
    private String securitynumber1;
    private String securitynumber2;
    private String calltype;
    private String callnumber;
}
