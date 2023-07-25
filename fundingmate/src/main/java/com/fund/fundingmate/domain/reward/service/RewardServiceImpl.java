//package com.fund.fundingmate.domain.reward.service;
//
//import com.fund.fundingmate.domain.reward.dto.RewardDTO;
//import com.fund.fundingmate.domain.reward.entity.Reward;
//import com.fund.fundingmate.domain.reward.repository.RewardFindRepository;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class RewardServiceImpl implements RewardListService {
//    @Autowired
//    private RewardFindRepository rewardFindRepository;
//    @Autowired
//    private ModelMapper modelMapper;
//    @Override
//    public List<RewardDTO> getSearchArticleList(String keyword)
//            throws Exception {
//        Page<Reward> lists = null;
//        if(keyword == null || keyword.equals("")) {
//            lists = rewardFindRepository.findAll();
//        } else {
//            lists = rewardFindRepository.findByproj_nameByinvest_proj_keywordByBusinessName(keyword);
//        }
//        List<RewardDTO> list = new ArrayList<>();
//        for(Reward article : lists.getContent()) {
//            list.add(modelMapper.map(article, RewardDTO.class));
//        }
//        return list;
//    }
//}
