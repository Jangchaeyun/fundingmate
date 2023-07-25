package com.fund.fundingmate.global.file.dto;

import com.fund.fundingmate.domain.reward.dto.RewardDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {
    private Long fileId;
    private String fileName;
    private Date fileRegistrationDate;
    private RewardDTO reward;
}