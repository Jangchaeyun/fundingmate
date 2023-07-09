package com.fund.fundingmate.global.file.entity;

import com.fund.fundingmate.domain.reward.entity.Reward;
import com.fund.fundingmate.domain.reward.entity.RewardType;
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "rep_img")
    private List<Reward> fundingimg;

    private String fileSavedName;

    private String fileOriginalName;

    private String filePath;

    private String fileSize;

    private Date fileRegistrationDate;
}