package com.fund.fundingmate.global.file.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fund.fundingmate.domain.investment.entity.Investment;
import com.fund.fundingmate.domain.reward.entity.Reward;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @OneToMany(mappedBy = "repfile", cascade = CascadeType.ALL)
    private List<Reward> fundingimg;
  /*  @ManyToOne(mappedBy = "investContentImgSavedName", cascade = CascadeType.ALL)
    private Investment investimg;*/

/*
    @ManyToOne
    @JoinColumn(name = "investContentImgSavedName_id")
    private Investment investContentImgSavedName;*/

    private String fileName;
//
//    private String filePath;
//
//    private String fileOriginalName;
//
//    private String fileSize;
//
/*private byte[] fileData;*/

    private Date fileRegistrationDate;
 /*   public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public byte[] getFileData() {
        return fileData;
    }*/

}
