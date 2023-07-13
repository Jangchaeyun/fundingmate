package com.fund.fundingmate.domain.investment.controller;

import com.fund.fundingmate.domain.investment.dto.InvestmentDTO;
import com.fund.fundingmate.domain.investment.service.InvestmentService;
import com.fund.fundingmate.global.file.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

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
