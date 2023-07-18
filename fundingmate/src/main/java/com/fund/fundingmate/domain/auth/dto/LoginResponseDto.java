package com.fund.fundingmate.domain.auth.dto;

import com.fund.fundingmate.domain.user.entity.User;
import lombok.Data;

@Data
public class LoginResponseDto {
    public boolean loginSuccess;
    public User user;
    public String accessToken;
    public String refreshToken;
}