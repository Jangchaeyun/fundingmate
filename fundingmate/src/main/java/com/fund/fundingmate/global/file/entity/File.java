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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reward_id")
    private Reward reward;

    private String fileName;
//
//    private String filePath;
//
//    private String fileOriginalName;
//
//    private String fileSize;
//


    private Date fileRegistrationDate;


}
