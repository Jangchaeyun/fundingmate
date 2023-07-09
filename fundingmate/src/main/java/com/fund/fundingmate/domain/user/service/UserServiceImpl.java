package com.fund.fundingmate.domain.user.service;

import com.fund.fundingmate.domain.user.entity.User;
import com.fund.fundingmate.domain.user.repository.UserRepository;
import com.fund.fundingmate.global.config.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserRepository userRepository;


    @Transactional
    public void join(User userDto){
        User user = User.builder()
                .userid(userDto.getUserid())
                .password(passwordEncoder.encode(userDto.getPassword()))  //비밀번호 인코딩
                .email(userDto.getEmail())
                .name(userDto.getUsername())
                .birthday(userDto.getBirthday())
                .tel(userDto.getTel())
                .notificationStatus("Y")
                .vitalization(1)
                .roles(Collections.singletonList("ROLE_USER"))         //roles는 최초 USER로 설정
                .build();

        userRepository.save(user).getId();
    }

    @Transactional
    public void login(User userDto){
        User user = userRepository.findByUserid(userDto.getUserid())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 아이디입니다."));
        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        // 로그인에 성공하면 id, roles 로 토큰 생성 후 반환
        jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
    }
}
