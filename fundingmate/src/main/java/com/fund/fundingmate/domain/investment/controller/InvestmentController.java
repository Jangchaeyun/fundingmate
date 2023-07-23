package com.fund.fundingmate.domain.investment.controller;

import com.fund.fundingmate.domain.investment.dto.InvestTypeDTO;
import com.fund.fundingmate.domain.investment.dto.InvestmentDTO;
import com.fund.fundingmate.domain.investment.dto.InvestmentDTO2;
import com.fund.fundingmate.domain.investment.service.InvestmentService;
import com.fund.fundingmate.global.file.Service.FileService;
import com.fund.fundingmate.global.file.dto.FileDTO;
import com.fund.fundingmate.global.file.entity.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.StandardOpenOption;

import javax.servlet.http.HttpSession;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class InvestmentController {

    @Autowired
    private InvestmentService investmentService;

    @Autowired
    private FileService fileService;

    @Autowired
    private HttpSession session;

    /*private static final String UPLOAD_DIRECTORY = "D:/yth/springboot-work/intellj/fundingmate/imgUpload";

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestPart(name = "file") MultipartFile file) {
        try {
            String originalFileName = file.getOriginalFilename();
            String fileSavedName = generateUniqueFileName(originalFileName);
            String filePath = UPLOAD_DIRECTORY + "/" + fileSavedName;

            java.io.File localFile = new java.io.File(filePath);
            localFile.getParentFile().mkdirs();

            file.transferTo(localFile);

            return new ResponseEntity<>("File uploaded successfully.", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to upload file.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String generateUniqueFileName(String originalFilename) {
        long timestamp = System.currentTimeMillis();
        return timestamp + "-" + originalFilename;
    }*/


    @PostMapping("/makeInvestHostinfo")
//    public ResponseEntity<InvestmentDTO> createInvestment( @RequestBody InvestmentDTO investmentDTO, @RequestParam("userId") Long userId) {
//    public ResponseEntity<InvestmentDTO> createInvestment( @ModelAttribute InvestmentDTO2 investmentDTO) {
    public ResponseEntity<InvestmentDTO> createInvestment( @RequestParam Map<String,Object> param) {
        try {

//
            Long savedInvestmentId = investmentService.createInvestment(param);
//
//            Map<String, Object> investmentMap = investmentService.getInvestmentById(savedInvestmentId);
//            InvestmentDTO createdInvestment = (InvestmentDTO) investmentMap.get("investment");
//            return ResponseEntity.status(HttpStatus.CREATED).body(createdInvestment);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/invest-detail/story/{investmentId}")
    public ResponseEntity<Map<String, Object>> getInvestmentById(@PathVariable("investmentId") Long investmentId) {
        try {
            Map<String, Object> result = investmentService.getInvestmentById(investmentId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
