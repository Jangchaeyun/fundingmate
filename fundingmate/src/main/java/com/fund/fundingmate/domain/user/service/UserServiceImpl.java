package com.fund.fundingmate.domain.user.service;

import com.fund.fundingmate.domain.user.entity.User;
import com.fund.fundingmate.domain.user.repository.UserRepository;
import com.fund.fundingmate.global.config.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Optional;

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
        // 현재 날짜 구하기
        LocalDate now = LocalDate.now();

        // 포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

        // 포맷 적용
        String formatedNow = now.format(formatter);

        User user = User.builder()
                .userid(userDto.getUserid())
                .password(passwordEncoder.encode(userDto.getPassword()))  //비밀번호 인코딩
                .email(userDto.getEmail())
                .name(userDto.getName())
                .birthday(userDto.getBirthday())
                .tel(userDto.getTel())
                .notificationStatus("Y")
                .vitalization(1)
                .joinDate(formatedNow)
                .roles(Collections.singletonList("ROLE_USER"))//roles는 최초 USER로 설정
                .snsLogin(userDto.getSnsLogin())
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

    @Override
    public void modifyPw(Long id, String password) {
        Optional<User> ouser = userRepository.findById(id);
        User user = ouser.get();
        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user).getId();
    }

}
