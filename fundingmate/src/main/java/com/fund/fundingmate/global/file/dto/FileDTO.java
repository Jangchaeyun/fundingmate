package com.fund.fundingmate.global.file.dto;

import com.fund.fundingmate.domain.reward.dto.RewardDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {
    private Long fileId;
    private List<RewardDTO> fundingimg;
    private String fileClassfication;
    private String fileSavedName;
    private String fileOriginalName;
    private String filePath;
    private String fileSize;
    private Date fileRegistrationDate;
}
