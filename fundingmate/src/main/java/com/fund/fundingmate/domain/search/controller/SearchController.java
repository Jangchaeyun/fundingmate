//package com.fund.fundingmate.domain.search.controller;
//
//import com.fund.fundingmate.domain.reward.dto.RewardDTO;
//import com.fund.fundingmate.domain.reward.service.RewardListService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//public class SearchController {
//    @Autowired
//    private RewardListService rewardListService;
//    @GetMapping("/search")
//    public ResponseEntity<Map<String,Object>> search(@RequestParam("word") String wordd) {
//        try {
//
//            List<RewardDTO> list = rewardListService.getSearchArticleList(keyword);
//            Map<String,Object> res = new HashMap<>();
//            res.put("list", list);
//            return new ResponseEntity<Map<String,Object>>(res, HttpStatus.OK);
//        } catch(Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
//        }
//    }
//
//}
