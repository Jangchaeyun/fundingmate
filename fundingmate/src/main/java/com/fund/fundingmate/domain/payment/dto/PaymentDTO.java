package com.fund.fundingmate.domain.payment.dto;

import com.fund.fundingmate.domain.investment.dto.InvestTypeDTO;
import com.fund.fundingmate.domain.reward.dto.RewardDTO;
import com.fund.fundingmate.domain.reward.dto.RewardTypeDTO;
import com.fund.fundingmate.domain.reward.entity.Reward;
import com.fund.fundingmate.domain.reward.entity.RewardType;
import com.fund.fundingmate.domain.user.dto.UserDTO;
import com.fund.fundingmate.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private Long id;

    private UserDTO user;

    private String cardnumber;

    private String cardpassword;

    private Integer paymentamount;

    private String payenddate;

    private String birthday;

    private String payperiod;

    private String shippingadress;

    private String shippingaddressdesc;

    private RewardTypeDTO rewardType;

    private InvestTypeDTO investType;
}
