package com.fund.fundingmate.domain.sms.controller;

import com.fund.fundingmate.domain.sms.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class EmailContorller {
    private final EmailService emailService;

    @PostMapping("/send-mail")
    public ResponseEntity<String> mailConfirm(@RequestParam("email") String email) throws Exception {
        try {
            System.out.println(email);
            String code = emailService.sendSimpleMessage(email);
            log.info("인증코드 : " + code);
            return new ResponseEntity<String>(code, HttpStatus.OK);
        }catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }
}
