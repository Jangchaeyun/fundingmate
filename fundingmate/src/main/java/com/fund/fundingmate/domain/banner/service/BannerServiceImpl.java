package com.fund.fundingmate.domain.banner.service;

import com.fund.fundingmate.domain.banner.dto.BannerDTO;
import com.fund.fundingmate.domain.banner.entity.Banner;
import com.fund.fundingmate.domain.banner.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService{
    @Autowired
    private BannerRepository bannerRepository;
    @Override
    public List<BannerDTO> getArticleList() throws Exception {
        return null;
    }
}
