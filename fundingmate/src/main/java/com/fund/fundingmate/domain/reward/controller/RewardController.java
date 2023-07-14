package com.fund.fundingmate.domain.reward.controller;

import com.fund.fundingmate.domain.reward.dto.RewardCommentDTO;
import com.fund.fundingmate.domain.reward.dto.RewardReplyDTO;
import com.fund.fundingmate.domain.reward.service.RewardCommentService;
import com.fund.fundingmate.domain.reward.service.RewardService;
import com.fund.fundingmate.global.file.Service.FileService;
import com.fund.fundingmate.global.file.entity.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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

    private RewardCommentDTO rewardCommentDTO;

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

    @GetMapping("/reward-detail/guide/{rewardId}")
    public ResponseEntity<Map<String, Object>> rewardDetailGuide(@PathVariable Long rewardId, HttpSession session) {
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
    public ResponseEntity<List<RewardCommentDTO>> rewardDetailContact(@PathVariable("rewardId") Long rewardId) {
        List<RewardCommentDTO> rewardComments = rewardCommentService.getRewardCommentsByRewardId(rewardId);

        if (rewardComments.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(rewardComments);
    }

    @PostMapping("/reward-detail/contact/{rewardId}")
    public ResponseEntity<List<RewardCommentDTO>> rewardDetailContactWrite(@PathVariable("rewardId") Long rewardId, @RequestBody RewardCommentDTO requestBody) {
       try {
           rewardCommentService.insertRewardComment(requestBody);

           List<RewardCommentDTO> rewardComments = rewardCommentService.getRewardCommentsByRewardId(rewardId);
           return new ResponseEntity<>(rewardComments, HttpStatus.OK);
       } catch (Exception e) {
           e.printStackTrace();
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    @DeleteMapping("/reward-detail/contact/comment/{commentId}")
    public ResponseEntity<List<RewardCommentDTO>> deleteRewardComment(@PathVariable("commentId") Long commentId) {
        try {
            rewardCommentService.deleteRewardComment(commentId);

            List<RewardCommentDTO> rewardComments = rewardCommentService.getRewardCommentsByRewardId(commentId);
            return new ResponseEntity<>(rewardComments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static final String UPLOAD_DIRECTORY = "E:/웹 애플리케이션 Full-Stack 과정/fundingmate/imgUpload";

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

    @PostMapping("/reward-detail/contact/comment/reply")
    public ResponseEntity<List<RewardCommentDTO>> rewardDetailContactReply(@RequestBody RewardReplyDTO requestBody) {
        try {
            rewardCommentService.insertRewardCommentReply(requestBody);

            List<RewardCommentDTO> rewardComments = rewardCommentService.getRewardCommentsByRewardId(requestBody.getRewardId());
            return new ResponseEntity<>(rewardComments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reward-detail/contact/comment/reply/{commentId}") // 경로 오타 수정
    public ResponseEntity<List<RewardReplyDTO>> rewardDetailCommentReplies(@PathVariable("commentId") Long commentId) {
        try {
            List<RewardReplyDTO> replies = rewardCommentService.getRewardCommentReplies(commentId);
            return ResponseEntity.ok(replies);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}