package com.fund.fundingmate.domain.reward.dto;

import com.fund.fundingmate.domain.user.dto.UserDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RewardCommentDTO {
    private Long id;
    private String comContent;
    private LocalDate comRegistrationDate;
    private LocalDate comRevisionDate;
    private RewardDTO reward;
    private UserDTO user;
    private List<RewardReplyDTO> replies;
}