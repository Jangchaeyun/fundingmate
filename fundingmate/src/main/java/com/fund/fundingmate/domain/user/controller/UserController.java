package com.fund.fundingmate.domain.user.controller;

import com.fund.fundingmate.domain.user.entity.User;
import com.fund.fundingmate.domain.user.repository.UserRepository;
import com.fund.fundingmate.domain.user.service.CustomUserDetailService;
import com.fund.fundingmate.domain.user.service.UserService;
import com.fund.fundingmate.global.config.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private JwtTokenProvider jwtTokenPrivider;
    @Autowired
    private UserService userService;
    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody User user) {
        try {
            System.out.println(user);
            userService.join(user);
            System.out.println(user);
            return new ResponseEntity<String>("회원가입 성공", HttpStatus.OK);
        }catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login(@RequestParam("id") String id,
                                                    @RequestParam("password") String password) {
        Map<String, Object> res = new HashMap<>();
        User user = (User)customUserDetailService.loadUserByUsername(id);

        if(user!=null && passwordEncoder.matches(password, user.getPassword())) {
            String accessToken = jwtTokenPrivider.createToken(user.getUsername(),user.getRoles());
            String refreshToken = jwtTokenPrivider.refreshToken(user.getUsername(),user.getRoles());
            res.put("userid", user.getUsername());
            res.put("accessToken", accessToken);
            res.put("refreshToken", refreshToken);
            return new ResponseEntity<Map<String,Object>>(res, HttpStatus.OK);
        }
        return new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/userInfo")
    public ResponseEntity<User> userInfo(@RequestParam("id") String id) {
        User user = (User)customUserDetailService.loadUserByUsername(id);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }
}
