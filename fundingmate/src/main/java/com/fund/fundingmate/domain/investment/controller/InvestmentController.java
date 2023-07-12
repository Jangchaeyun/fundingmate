package com.fund.fundingmate.domain.investment.controller;

import com.fund.fundingmate.domain.investment.dto.InvestmentDTO;
import com.fund.fundingmate.domain.investment.entity.Investment;
import com.fund.fundingmate.domain.reward.dto.RewardCommentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class InvestmentController {
    /*@PostMapping("/investment-detail/contact/{investmentId}")
    public InvestmentEntity<InvestmentDTO> rewardDetailContactWrite(@PathVariable("investmentId") Long investmentId, @RequestBody RewardCommentDTO requestBody) {
        try {
            rewardCommentService.insertRewardComment(requestBody);

            List<RewardCommentDTO> rewardComments = rewardCommentService.getRewardCommentsByRewardId(rewardId);
            return new ResponseEntity<>(rewardComments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
*/
}
