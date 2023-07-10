package com.fund.fundingmate.domain.reward.controller;

import com.fund.fundingmate.domain.reward.dto.RewardCommentDTO;
import com.fund.fundingmate.domain.reward.service.RewardCommentService;
import com.fund.fundingmate.domain.reward.service.RewardService;
import com.fund.fundingmate.global.file.Service.FileService;
import com.fund.fundingmate.global.file.entity.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@RestController
public class RewardController {
    @Autowired
    private RewardService rewardService;

    @Autowired
    private FileService fileService;

    @Autowired
    private HttpSession session;

    @Autowired
    private RewardCommentService rewardCommentService;

    @GetMapping("/reward-detail/story/{rewardId}")
    public ResponseEntity<Map<String, Object>> rewardDetailStory(@PathVariable Long rewardId) {
        try {
            String userId = (String) session.getAttribute("userId");
            System.out.println("rewardDetail: " + userId);
            Map<String, Object> rewardDetail = rewardService.getRewardById(rewardId);
            return new ResponseEntity<Map<String, Object>>(rewardDetail, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/reward-detail/contact/{rewardId}")
    public ResponseEntity<RewardCommentDTO> rewardDetailContact(@PathVariable("rewardId") Long rewardId) {
        RewardCommentDTO rewardCommentDTO = rewardCommentService.getRewardCommentByRewardId(rewardId);

        if (rewardCommentDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(rewardCommentDTO);
    }

    private static final String UPLOAD_DIRECTORY = "D:/웹 애플리케이션 Full-Stack 과정/fundingmate/imgUpload";

    @GetMapping("/img/{fileOriginalName}")
    public void imageView(@PathVariable String fileOriginalName, HttpServletResponse response) {
        try {
            String filePath = UPLOAD_DIRECTORY + "/" + fileOriginalName;

            Path imagePath = Paths.get(filePath);
            byte[] fileBytes = Files.readAllBytes(imagePath);

            response.setContentType("image/*");

            response.getOutputStream().write(fileBytes);
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}