package com.fund.fundingmate.domain.investment.controller;

import com.fund.fundingmate.domain.investment.dto.InvestmentDTO;
import com.fund.fundingmate.domain.investment.entity.Investment;
import com.fund.fundingmate.domain.investment.service.InvestmentService;
import com.fund.fundingmate.global.file.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
    @PostMapping("/investment-detail/contact/{investmentId}")
    public ResponseEntity<String> createInvestment( @PathVariable("investmentId") Long userId,  @RequestBody InvestmentDTO investmentDTO) {
        try {
            Investment investment = investmentService.createInvestment(investmentDTO, userId);
           /* return new ResponseEntity<InvestmentDTO>(investment, HttpStatus.OK);*/
            return ResponseEntity.ok().body(new InvestmentDTO(investment));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);

        }
    }

}
