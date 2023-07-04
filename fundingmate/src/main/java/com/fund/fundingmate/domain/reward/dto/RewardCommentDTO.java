package com.fund.fundingmate.domain.reward.dto;

import com.fund.fundingmate.domain.user.dto.UserDTO;
import lombok.Data;

import java.util.Date;

@Data
public class RewardCommentDTO {
    private Long id;
    private String comTitle;
    private String comContent;
    private Date comRegistration;
    private Date comRevisionDate;
    private RewardDTO reward;
    private UserDTO user;
}