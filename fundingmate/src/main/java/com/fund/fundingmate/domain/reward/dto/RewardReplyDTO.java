package com.fund.fundingmate.domain.reward.dto;

import com.fund.fundingmate.domain.reward.entity.RewardReply;
import com.fund.fundingmate.domain.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RewardReplyDTO {
    private Long id;
    private String repContent;
    private Date repRegisterationDate;
    private Date repRevisionDate;
    private Long rewardId;
    private Long commentId;
    private Long userId;
}
