package com.fund.fundingmate.domain.investment.controller;

import com.fund.fundingmate.domain.investment.dto.InvestTypeDTO;
import com.fund.fundingmate.domain.investment.dto.InvestmentDTO;
import com.fund.fundingmate.domain.investment.dto.InvestmentDTO2;
import com.fund.fundingmate.domain.investment.service.InvestmentService;

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
    private HttpSession session;

    @PostMapping("/makeInvestHostinfo")
//    public ResponseEntity<InvestmentDTO> createInvestment( @RequestBody InvestmentDTO investmentDTO, @RequestParam("userId") Long userId) {
//    public ResponseEntity<InvestmentDTO> createInvestment( @ModelAttribute InvestmentDTO2 investmentDTO) {
//    public ResponseEntity<InvestmentDTO> createInvestment( @RequestParam Map<String,Object> param) {
    public ResponseEntity<InvestmentDTO> createInvestment(@ModelAttribute InvestmentDTO investmentDTO,
                                                          @RequestParam("userId") Long userId,
                                                          @RequestParam("cards") String cards,
                                                          @RequestParam("investRepImg") MultipartFile repFile,
                                                          @RequestParam("investContentImg") MultipartFile[] contentFiles,
                                                          @RequestParam("investBusinessLicenseImg" )  MultipartFile businessFile,
                                                          @RequestParam("investBankAccountImg") MultipartFile bankFile) {
        try {
            System.out.println(userId);
            System.out.println(contentFiles.length);
            Long savedInvestmentId = investmentService.createInvestment(investmentDTO, userId, cards, repFile, contentFiles, businessFile, bankFile);
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
