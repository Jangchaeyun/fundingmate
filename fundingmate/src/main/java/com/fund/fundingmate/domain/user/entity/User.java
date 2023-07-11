package com.fund.fundingmate.domain.user.entity;

import com.fund.fundingmate.domain.user.dto.UserDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userid;

    private String password;

    private String email;

    private String name;

    private String birthday;

    private String tel;

    private String notificationStatus;

    private Integer vitalization;

    public UserDTO toDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(this.id);
        return userDTO;
    }


}
