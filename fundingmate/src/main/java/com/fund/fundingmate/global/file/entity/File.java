package com.fund.fundingmate.global.file.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "file")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;

    private String fileClassfication;

    private String fileSavedName;

    private String fileOriginalName;

    private String filePath;

    private String fileSize;

    private Date fileRegistrationDate;
}