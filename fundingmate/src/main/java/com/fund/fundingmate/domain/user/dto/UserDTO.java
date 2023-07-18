package com.fund.fundingmate.domain.user.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String userid;
    private String password;
    private String email;
    private String name;
    private String birthday;
    private String tel;
    private String notificationStatus;
    private Integer vitalization;
    private String snsLogin;
    private String joinDate;
}
