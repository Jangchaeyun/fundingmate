package com.fund.fundingmate.domain.investment.entity;

import com.fund.fundingmate.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "invest_comment")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InvestComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commNo;

    private String commTitle;

    private String commContent;

    private Date commRegistrationDate;

    private Date comRevisionDate;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @ManyToOne
    @JoinColumn(name = "invest_no")
    private InvestComment investComment;
}
