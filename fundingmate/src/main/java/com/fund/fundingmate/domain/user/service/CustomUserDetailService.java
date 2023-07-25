package com.fund.fundingmate.domain.user.service;

import com.fund.fundingmate.domain.user.entity.User;
import com.fund.fundingmate.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 사용자 정보를 데이터베이스에서 조회합니다.
        Optional<User> ouser =  userRepository.findByUserid(username);
        User user =  ouser.get();
        // 사용자 정보가 없는 경우 예외 처리
        if (user == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        // 사용자 정보를 기반으로 UserDetails 객체를 생성하여 반환합니다.
        return buildUserDetails(user);
    }
    private UserDetails buildUserDetails(User user) {
        // 사용자 정보를 기반으로 UserDetails 객체를 생성합니다.
        // 필요한 정보를 설정하고, 권한 정보를 포함시킬 수 있습니다.
        return User.builder()
                .userid(user.getUsername())
                .password(user.getPassword())
                .roles(List.of(user.getRoles().toArray(new String[0])))
                .build();
    }
}
