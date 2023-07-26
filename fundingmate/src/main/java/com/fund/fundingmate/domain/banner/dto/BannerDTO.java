package com.fund.fundingmate.domain.banner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BannerDTO {
	private Long bnNo;
	private Long bnShowno;
	private String bn_classification;
	private String bn_title;
	private String bn_content;
	private Long bn_imgno;
	private String bn_usewhether;
	private String bn_link;
	private String bn_startdate;
	private String bn_enddate;
	private String bn_adddate;
}
