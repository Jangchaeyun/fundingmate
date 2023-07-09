package com.fund.fundingmate.domain.reward.controller;

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

    @GetMapping("/reward-detail/story/{rewardJd}")
    public ResponseEntity<Map<String, Object>> rewardDetail(@PathVariable Long rewardJd) {
        try {
            String userId = (String) session.getAttribute("userId");
            System.out.println("rewardDetail: " + userId);
            Map<String, Object> rewardDetail = rewardService.getRewardById(rewardJd);
            return new ResponseEntity<Map<String, Object>>(rewardDetail, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/img/{fileOriginalName}")
    public void imageView(@PathVariable String fileOriginalName, HttpServletResponse response) {
        try {
            File file = fileService.getFileByOriginalName(fileOriginalName);
            if (file != null && file.getFilePath() != null) {
                Path filePath = Paths.get(file.getFilePath());
                if (Files.exists(filePath)) {
                    response.setContentType("image/jpeg/jpg/png");
                    Files.copy(filePath, response.getOutputStream());
                    response.getOutputStream().flush();
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}