package com.fund.fundingmate.domain.banner.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fund.fundingmate.domain.reward.entity.Reward;
import com.fund.fundingmate.domain.user.entity.User;
import com.fund.fundingmate.global.file.entity.File;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Banner {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long bnNo;
    @Column
    private Long bnShowno;
    @Column
    private String bn_classification;
    @Column
    private String bn_title;
    @Column
    private String bn_content;
    @Column
    private Long bn_imgno;
    @Column
    private String bn_usewhether;
    @Column
    private String bn_link;
    @Column
    private Date bn_startdate;
    @Column
    private Date bn_enddate;
    @Column
    private Date bn_adddate;

//    @OneToMany(mappedBy = "repfile", cascade = CascadeType.ALL)
//    private List<Reward> fundingimg;

//    @OneToMany(mappedBy="articleLike")
//    private List<Likes> likes = new ArrayList<>();
//
//    @Builder
//    public Article(String title, String content, User user, String fileName) {
//        this.title=title;
//        this.content=content;
//        this.user=user;
//        this.fileName=fileName;
//    }

    @Override
    public String toString() {
        return String.format("[%d,%s,%s,%s,%d,%s,%s,%s,%s]", bnNo,bn_classification,bn_title,bn_content,bn_imgno,bn_usewhether,bn_link,bn_startdate,bn_enddate,bn_adddate);
    }
}
