package com.fund.fundingmate.global.code.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "code")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Code {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long codeId;

    private  String fileClassification;

    private String fileName;

    private Date fileRegistrationDate;
}
