package com.fund.fundingmate.domain.user.controller;

import com.fund.fundingmate.domain.user.dto.UserDTO;
import com.fund.fundingmate.domain.user.entity.User;
import com.fund.fundingmate.domain.user.repository.UserRepository;
import com.fund.fundingmate.domain.user.service.CustomUserDetailService;
import com.fund.fundingmate.domain.user.service.UserService;
import com.fund.fundingmate.global.config.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
            return new ResponseEntity<String>("회원가입 성공", HttpStatus.OK);
        }catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login(@RequestParam("id") String id,
                                                    @RequestParam("password") String password
            /*,@RequestParam(value = "remember-me", required = false) boolean rememberMe*/) {
        Map<String, Object> res = new HashMap<>();
        try{
            User user = (User)customUserDetailService.loadUserByUsername(id);
            Optional<User> userDto = userRepository.findByUserid(user.getUsername());
            user = userDto.get();
            if(user!=null && passwordEncoder.matches(password, user.getPassword())) {
                String accessToken = jwtTokenPrivider.createToken(user.getUsername(),user.getRoles());
                String refreshToken = jwtTokenPrivider.refreshToken(user.getUsername(),user.getRoles());
                res.put("userid", user.getUsername());
                res.put("id",user.getId());
                res.put("accessToken", accessToken);
                res.put("refreshToken", refreshToken);
                return new ResponseEntity<Map<String,Object>>(res, HttpStatus.OK);
            }
            res.put("err","아이디 또는 비밀번호가 틀립니다.");
            return new ResponseEntity<Map<String,Object>>(res,HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            e.printStackTrace();
            res.put("err","아이디 또는 비밀번호가 틀립니다.");
            return new ResponseEntity<Map<String,Object>>(res,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/userInfo")
    public ResponseEntity<User> userInfo(@RequestParam("id") String id) {
        User user = (User)customUserDetailService.loadUserByUsername(id);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }
    @PostMapping("/useridChk")
    public ResponseEntity<Boolean> useridChk(@RequestParam("id") String id) {
        Optional<User> ouser = userRepository.findByUserid(id);
        Boolean useridChk = !ouser.isPresent();
        return new ResponseEntity<Boolean>(useridChk,HttpStatus.OK);
    }

    @PostMapping("/findById")
    public ResponseEntity<User> findById(@RequestBody UserDTO userDto) {

        String name = userDto.getName();
        String tel = userDto.getTel();
        String email = userDto.getEmail();
        User user = null;
        System.out.println(name);
        System.out.println(tel);
        System.out.println(email);
        if (email == null || email == "") {
            // 이름과 전화번호로 아이디 검색
            user = userRepository.findByNameAndTel(name, tel);
            System.out.println(user);
        }else if(tel == null || tel == "") {
            user = userRepository.findByNameAndEmail(name, email);
            System.out.println(user);
        }
        if (user != null) {
            return new ResponseEntity<User>(user,HttpStatus.OK);
        }else {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/findByPw")
    public ResponseEntity<User> findByPw(@RequestBody UserDTO userDto) {
        String userid = userDto.getUserid();
        String name = userDto.getName();
        String tel = userDto.getTel();
        String email = userDto.getEmail();
        System.out.println(userid);
        System.out.println(name);
        System.out.println(tel);
        System.out.println(email);
        User user = null;
        if (email == null || email == "") {
            // 이름과 전화번호로 아이디 검색
            user = userRepository.findByNameAndTelAndUserid(name,tel,userid);
            System.out.println(user);
        }
        else if(tel == null || tel == "") {
            user = userRepository.findByNameAndEmailAndUserid(name, email, userid);
            System.out.println(user);
        }
        if (user != null) {
            return new ResponseEntity<User>(user,HttpStatus.OK);
        }else {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/modifyPw")
    public ResponseEntity<String> modifyPw(@RequestParam("id") Long id,@RequestParam("password") String password) {
        try {
            Optional<User> userDto = userRepository.findById(id);
            User user = userDto.get();
            if(user!=null && !passwordEncoder.matches(password, user.getPassword())) {
                userService.modifyPw(id,password);
                return new ResponseEntity<String>("비밀번호 재설정 완료", HttpStatus.OK);
            }else {
                return new ResponseEntity<String>("이전의 설정하신 비밀번호로는 설정 할 수 없습니다.", HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
