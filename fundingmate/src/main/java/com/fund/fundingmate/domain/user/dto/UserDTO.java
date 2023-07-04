package com.fund.fundingmate.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long userNo;
    private String id;
    private String password;
    private String email;
    private String name;
    private String birthday;
    private String tel;
    private String notificationStatus;
    private Integer vitalization;
}
