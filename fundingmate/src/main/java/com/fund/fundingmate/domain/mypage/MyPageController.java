package com.fund.fundingmate.domain.mypage;

import com.fund.fundingmate.domain.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @PutMapping("/updateEmail")
    public ResponseEntity<UserDTO> updateEmail(@RequestBody Map<String, String> request) {
        String userId = request.get("id");
        String newEmail = request.get("email");

        if (userId == null || newEmail == null) {
            return ResponseEntity.badRequest().build();
        }

        // Assuming you have a method in the MyPageService to handle the email update
        UserDTO updatedUser = myPageService.updateEmail(userId, newEmail);

        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateTel")
    public ResponseEntity<UserDTO> updateTel(@RequestBody Map<String, String> request) {
        String userId = request.get("id");
        String newTel = request.get("tel");

        if (userId == null || newTel == null) {
            return ResponseEntity.badRequest().build();
        }

        // Assuming you have a method in the MyPageService to handle the telephone number update
        UserDTO updatedUser = myPageService.updateTel(userId, newTel);

        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<UserDTO> updatePassword(@RequestBody Map<String, String> request) {
        String userId = request.get("id");
        String newPassword = request.get("password");

        if (userId == null || newPassword == null) {
            return ResponseEntity.badRequest().build();
        }

        // Assuming you have a method in the MyPageService to handle the password update
        UserDTO updatedUser = myPageService.updatePassword(userId, newPassword);

        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
