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
public class PaymentDTO {
    private Long id;

    private User user;

    private String cardnumber;

    private String cardpassword;

    private Boolean paymentcode;

    private Integer paymentamount;

    private String payenddate;

    private String birthday;

    private String payperiod;

    private String shippingadress;

    private String shippingadressdesc;
}
