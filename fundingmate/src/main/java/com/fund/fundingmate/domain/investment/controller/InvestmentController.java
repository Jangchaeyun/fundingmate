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

    //이거 참고해서 컨트롤러 만들기
  /*  @PostMapping("/investment-detail/contact/{investmentId}")
    public InvestmentEntity<InvestmentDTO> rewardDetailContactWrite(@PathVariable("investmentId") Long investmentId, @RequestBody RewardCommentDTO requestBody) {
        try {
            rewardCommentService.insertRewardComment(requestBody);

            List<RewardCommentDTO> rewardComments = rewardCommentService.getRewardCommentsByRewardId(rewardId);
            return new ResponseEntity<>(rewardComments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

   /* // InvestmentDTO를 Investment 엔티티로 변환
   *//*         Investment investment = investmentService.convertToInvestment(investmentDTO);

            // Investment 엔티티를 저장
            investmentService.saveInvestment(investment);*/
 /*   @PostMapping("/investment-detail/contact/{investmentId}")
    public ResponseEntity<String> createInvestment( @PathVariable("investmentId") Long userId,  @RequestBody InvestmentDTO investmentDTO) {
        try {
            investmentService.createInvestment(investmentDTO, userId);
            return new ResponseEntity<InvestmentDTO>(investment, HttpStatus.OK);//inve~
            return ResponseEntity.ok().body(new InvestmentDTO(investment));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);

        }
    }*/
/*
   @PostMapping("/create")
   public ResponseEntity<Integer> createInvestment(@RequestBody InvestmentDTO investmentDTO) {
       try {
           investmentService.createInvestment(investmentDTO, userId);
           investmentService.getInvestmentById(investmentId);
           //service인베스트먼트 아이디..셀렉트문... 인티져형으로// 프로젝트 넘버 (서비스)
           return new ResponseEntity<Integer>(investmentId, HttpStatus.OK);

       } catch (Exception e) {
           e.printStackTrace();
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
   }
*/
  /* @PostMapping("/make-invest/create")
   public ResponseEntity<String> createInvestment(@RequestBody InvestmentDTO investmentDTO , @RequestParam("userId") Long userId) {
       try {
           investmentService.createInvestment(investmentDTO, userId);
           // investmentService.getInvestmentById(investmentId);
           // service인베스트먼트 아이디..셀렉트문... 인티져형으로// 프로젝트 넘버 (서비스)
           *//*return ResponseEntity.ok("투자가 성공적으로 생성되었습니다.");*//*
           return ResponseEntity.status(HttpStatus.CREATED).body("Investment created successfully");


       } catch (Exception e) {
           e.printStackTrace();
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
   }*/

/*
    @PostMapping("/make-invest/hostinfo")
    public ResponseEntity<InvestmentDTO> createInvestment(@RequestBody InvestmentDTO investmentDTO , @RequestParam("userId") Long userId) {
        try {
            investmentService.createInvestment(investmentDTO, userId);

            InvestmentDTO createdInvestment = (InvestmentDTO) investmentService.getInvestmentById(investmentDTO.getId()); // 예시: 생성된 투자 정보 조회
            return ResponseEntity.status(HttpStatus.CREATED).body(createdInvestment);



        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
*/

   /* @PostMapping("/make-invest/hostinfo")
    public ResponseEntity<InvestmentDTO> createInvestment(@RequestBody InvestmentDTO investmentDTO, @RequestParam("userId") Long userId) {
        try {
            // Base64 문자열을 List<FileDTO> 객체로 변환하여 설정
            List<FileDTO> fileDTOList = new ArrayList<>();
            if (investmentDTO.getInvestContentImgSavedName() != null) {
                for (FileDTO base64Data : investmentDTO.getInvestContentImgSavedName()) {
                    if (base64Data.getFileData() != null) {
                        byte[] fileBytes = Base64.decodeBase64(base64Data.getFileData());
                        FileDTO fileDTO = new FileDTO();
                        fileDTO.setFileData(fileBytes);
                        fileDTOList.add(fileDTO);
                    }
                }
            }
            investmentDTO.setInvestContentImgSavedName(fileDTOList); // investmentDTO에 파일 정보 설정

            // 개별 파일 처리
            // 개별 파일 처리
           *//* MultipartFile investRepImgSavedNameFile = convertToFile(investmentDTO.getInvestRepImgSavedName());
            File savedInvestRepImgSavedName = fileService.saveFile(null, investRepImgSavedNameFile);
            investmentDTO.setInvestRepImgSavedName(modelMapper.map(savedInvestRepImgSavedName, FileDTO.class));

            MultipartFile investIdBusinessLicenseImgSavedNameFile = convertToFile(investmentDTO.getInvestIdBusinessLicenseImgSavedName());
            File savedInvestIdBusinessLicenseImgSavedName = fileService.saveFile(null, investIdBusinessLicenseImgSavedNameFile);
            investmentDTO.setInvestIdBusinessLicenseImgSavedName(modelMapper.map(savedInvestIdBusinessLicenseImgSavedName, FileDTO.class));

            MultipartFile investBankAccountCopyImgSavedNameFile = convertToFile(investmentDTO.getInvestBankAccountCopyImgSavedName());
            File savedInvestBankAccountCopyImgSavedName = fileService.saveFile(null, investBankAccountCopyImgSavedNameFile);
            investmentDTO.setInvestBankAccountCopyImgSavedName(modelMapper.map(savedInvestBankAccountCopyImgSavedName, FileDTO.class));
*//*
            investmentService.createInvestment(investmentDTO, userId);

            InvestmentDTO createdInvestment = (InvestmentDTO) investmentService.getInvestmentById(investmentDTO.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdInvestment);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
*/

    @PostMapping("/make-invest/hostinfo")
    public ResponseEntity<InvestmentDTO> createInvestment( @RequestBody InvestmentDTO investmentDTO, @RequestParam("userId") Long userId) {
        try {

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
