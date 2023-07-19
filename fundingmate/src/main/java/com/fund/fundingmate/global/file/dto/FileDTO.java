package com.fund.fundingmate.global.file.dto;

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
//    private String fileSavedName;
//    private String fileOriginalName;
//    private String filePath;
//    private String fileSize;


    private Date fileRegistrationDate;



}