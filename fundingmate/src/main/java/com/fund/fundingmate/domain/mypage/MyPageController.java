package com.fund.fundingmate.domain.mypage;

import com.fund.fundingmate.domain.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyPageController {

    @Autowired
    private MyPageService myPageService;

    @GetMapping("/myPage/{id}")
    public ResponseEntity<UserDTO> getMyPageData(@RequestParam String id) {
        // Assuming you have a method in the UserService to fetch basic user data
        UserDTO userData = myPageService.getBasicUserData(id);

        if (userData != null) {
            return ResponseEntity.ok(userData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
