package com.fund.fundingmate.domain.main.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fund.fundingmate.domain.investment.entity.Investment;
import com.fund.fundingmate.domain.main.repository.MainRepository;
import com.fund.fundingmate.domain.reward.entity.Reward;
import com.querydsl.core.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ProjectListController {
    @Autowired
    private MainRepository mainRepository;

    @GetMapping("/rewardList")
    public ResponseEntity<List> rewardList(@RequestParam(value = "word",required = false) String word) {
        try {
            List<Tuple> tuples = mainRepository.findRewardAll(word);
            List data = new ArrayList();
            for(Tuple tuple:tuples){
//                System.out.println("머임" );
//                System.out.println(tuple.size());
                Reward reward = tuple.get(0,Reward.class);
//                System.out.println("reward: " + reward.toString());
                Integer paymentamountSum  =  tuple.get(1,Integer.class);
//                Long rewardCtn  =  tuple.get(2,Long.class);
//                System.out.println("rewardCtn : " + rewardCtn );
//                System.out.println("paymentamountSum : " + paymentamountSum );
//                int a = tuple.get(1,Integer.class);
//                System.out.println("a: " + a);
//                System.out.println("확인용 ");

                ObjectMapper rewardmapper = new ObjectMapper();
                Map<String, Object> totmap =  rewardmapper.convertValue(reward, Map.class);
//                System.out.println("totmap:"+totmap);
                totmap.put("paymentamountSum",paymentamountSum);
                //ObjectMapper paymentamountmapper = new ObjectMapper();

                //totmap.putAll(paymentamountmapper.convertValue(paymentamountSum, Map.class));
                data.add(totmap);

            }
            return new ResponseEntity<List>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/InvestList")
    public ResponseEntity<List> investList(@RequestParam(value = "word",required = false) String word) {
        try {
            System.out.println("인베스트 확인이요");
            List<Tuple> tuples = mainRepository.findInvestAll(word);
            List data = new ArrayList();
            for(Tuple tuple:tuples){
//                System.out.println(tuple.size());
                Investment investment = tuple.get(0,Investment.class);
//                System.out.println("investment: " + investment.toString());
                Integer paymentamountSum  =  tuple.get(1,Integer.class);
//                System.out.println("paymentamountSum : " + paymentamountSum );
//                int a = tuple.get(1,Integer.class);
//                System.out.println("a: " + a);
//                System.out.println("확인용 ");

                ObjectMapper investmapper = new ObjectMapper();
                Map<String, Object> totmap =  investmapper.convertValue(investment, Map.class);
//                System.out.println("totmap:"+totmap);
                totmap.put("paymentamountSum",paymentamountSum);
                //ObjectMapper paymentamountmapper = new ObjectMapper();

                //totmap.putAll(paymentamountmapper.convertValue(paymentamountSum, Map.class));
                data.add(totmap);
//                System.out.println("인베스트 확인이요2");
            }
//                System.out.println(data);

            return new ResponseEntity<List>(data, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List>(HttpStatus.BAD_REQUEST);
        }
    }

}
