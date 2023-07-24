package com.fund.fundingmate.domain.banner.service;

import com.fund.fundingmate.domain.banner.dto.BannerDTO;

import java.util.List;

public interface BannerService {
    List<BannerDTO> getArticleList() throws Exception;
}
