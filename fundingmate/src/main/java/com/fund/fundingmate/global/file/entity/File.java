package com.fund.fundingmate.global.file.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"reward"})
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;

//    @OneToMany(mappedBy = "repfile", cascade = CascadeType.ALL)
//    private List<Reward> fundingimg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reward_id")
    private Reward reward;

    private String fileName;
    private String fileClassification;
    private String fileOriginalName;
    private String fileSavedName;
    private String filePath;
    private String fileSize;



    private Date fileRegistrationDate;

    public File(String fileName) {
        this.fileName = fileName;
    }
}
