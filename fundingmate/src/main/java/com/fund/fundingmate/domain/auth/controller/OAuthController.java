package com.fund.fundingmate.domain.auth.controller;

import com.fund.fundingmate.domain.auth.dto.LoginResponseDto;
import com.fund.fundingmate.domain.auth.service.AuthService;
import com.fund.fundingmate.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class OAuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/login/kakao")
    @ResponseBody
    public ResponseEntity<LoginResponseDto> kakaoLogin(@RequestParam String code) {
        String kakaoAccessToken = authService.getKakaoAccessToken(code).getAccess_token();
        return authService.kakaoLogin(kakaoAccessToken);

//        Map<String, Object> res = new HashMap<>();
//        User user = (User)customUserDetailService.loadUserByUsername(id);
//
//        if(user!=null && passwordEncoder.matches(password, user.getPassword())) {
//            String accessToken = jwtTokenPrivider.createToken(user.getUsername(),user.getRoles());
//            String refreshToken = jwtTokenPrivider.refreshToken(user.getUsername(),user.getRoles());
//            res.put("userid", user.getUsername());
//            res.put("accessToken", accessToken);
//            res.put("refreshToken", refreshToken);
//            return new ResponseEntity<Map<String,Object>>(res, HttpStatus.OK);
//        }
//        return new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
//        return new ResponseEntity<Map<String,Object>>(res, HttpStatus.OK);
    }
    @GetMapping("/login/naver")
    @ResponseBody
    public ResponseEntity<LoginResponseDto> naverLogin(@RequestParam String code) {
        String kakaoAccessToken = authService.getKakaoAccessToken(code).getAccess_token();
        return authService.kakaoLogin(kakaoAccessToken);
    }
//    @GetMapping("/oauth")
//    public ResponseEntity<String> naverConnect() throws UnsupportedEncodingException {
//        System.out.println("test");
//        String url = authService.createNaverURL();
//
//        return new ResponseEntity<String>(url, HttpStatus.OK); // 프론트 브라우저로 보내는 주소
//    }
}
