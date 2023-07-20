package com.fund.fundingmate.domain.investment.controller;

import com.fund.fundingmate.domain.investment.dto.InvestTypeDTO;
import com.fund.fundingmate.domain.investment.dto.InvestmentDTO;
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
import java.nio.file.StandardOpenOption;

import javax.servlet.http.HttpSession;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
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

    @PostMapping("/makeInvestHostinfo")
    public ResponseEntity<InvestmentDTO> createInvestment( @RequestBody InvestmentDTO investmentDTO, @RequestParam("userId") Long userId) {
        try {

            System.out.println(userId);
            System.out.println(investmentDTO);
            System.out.println(investmentDTO.getInvestTypes());

            Long savedInvestmentId = investmentService.createInvestment(investmentDTO, userId);

            Map<String, Object> investmentMap = investmentService.getInvestmentById(savedInvestmentId);
            InvestmentDTO createdInvestment = (InvestmentDTO) investmentMap.get("investment");
            return ResponseEntity.status(HttpStatus.CREATED).body(createdInvestment);
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
