package com.fund.fundingmate;

import com.fund.fundingmate.domain.reward.dto.RewardDTO;
import com.fund.fundingmate.domain.reward.dto.RewardOptionDTO;
import com.fund.fundingmate.domain.reward.dto.RewardTypeDTO;
import com.fund.fundingmate.domain.reward.service.RewardService;
import com.fund.fundingmate.domain.user.repository.UserRepository;
import com.fund.fundingmate.domain.user.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FundingmateApplicationTests {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RewardService rewardService;

	@Test
	void insertMember() {
		User user = new User();

		user.setBirthday("1995/05/14");
		user.setEmail("yun0708@naver.com");
		user.setId("yun0708");
		user.setName("윤태희");
		user.setNotificationStatus("Y");
		user.setPassword("198742");
		user.setTel("010-3345-1589");
		user.setVitalization(0);

		userRepository.save(user);
	}

	@Test
	void insertReward() {
		// Create a user for the reward
		User user = new User();
		user.setBirthday("1999/07/04");
		user.setEmail("gee0704@naver.com");
		user.setId("gee0704");
		user.setName("지태현");
		user.setNotificationStatus("Y");
		user.setPassword("195871");
		user.setTel("010-1258-9854");
		user.setVitalization(0);
		userRepository.save(user);

		RewardDTO rewardDTO = new RewardDTO();
		rewardDTO.setProjName("My Project");
		rewardDTO.setProjTargetAmout(10000);
		rewardDTO.setProjDateStart(LocalDate.of(2023, 7, 1));
		rewardDTO.setProjDateEnd(LocalDate.of(2023, 7, 31));
		rewardDTO.setRewardRepImgSavedName("project_image.jpg");
		rewardDTO.setProjKeyWord("technology, innovation");
		rewardDTO.setRewardVideoAddress("https://www.youtube.com/watch?v=abcd1234");
		rewardDTO.setRewardContentImgSavedName("content_image.jpg");
		rewardDTO.setProjContent("Project content goes here");
		rewardDTO.setRewardRefundExchangePolicy("Refund and exchange policy details");
		rewardDTO.setRewardContact("Contact information");
		rewardDTO.setRewardEmail("reward@example.com");
		rewardDTO.setRewardCategory("Technology");
		rewardDTO.setModelName("Model X");
		rewardDTO.setCountryOfOrigin("United States");
		rewardDTO.setManufacturer("Tesla Inc.");
		rewardDTO.setAsPhoneNumber("123-456-7890");
		rewardDTO.setRewardIdBusinessLicenseImgSavedName("business_license_image.jpg");
		rewardDTO.setBusinessAddress("Business address");
		rewardDTO.setBank("Bank name");
		rewardDTO.setAccNumber("1234567890");
		rewardDTO.setDepositorName("Depositor name");
		rewardDTO.setRewardBankAccountCopyImgSavedName("bank_account_copy_image.jpg");
		rewardDTO.setTaxBillEmail("tax@example.com");
		rewardDTO.setWebsiteUrl("https://www.example.com");
		rewardDTO.setFacebookUrl("https://www.facebook.com/example");
		rewardDTO.setInstagramUrl("https://www.instagram.com/example");
		rewardDTO.setBlogUrl("https://www.example.com/blog");
		rewardDTO.setTwitterUrl("https://twitter.com/example");

		// Create reward type DTOs
		List<RewardTypeDTO> rewardTypeDTOs = new ArrayList<>();
		RewardTypeDTO rewardTypeDTO = new RewardTypeDTO();
		rewardTypeDTO.setRewardAmount(500);
		rewardTypeDTO.setRewardAvailableLimit(true);
		rewardTypeDTO.setRewardAvailableCount(5);
		rewardTypeDTO.setRewardTitle("Reward Type 1");
		rewardTypeDTO.setRewardContent("Reward Type 1 Content");
		// Set other properties of the rewardTypeDTO

		// Create reward option DTO
		RewardOptionDTO rewardOptionDTO = new RewardOptionDTO();
		rewardOptionDTO.setRewardOptName("Option 1");
		rewardOptionDTO.setRewardOptCon("Option 1 Content");
		rewardTypeDTO.setRewardOption(rewardOptionDTO);

		rewardTypeDTOs.add(rewardTypeDTO);
		rewardDTO.setRewardTypes(rewardTypeDTOs);

		Long userId = user.getUserNo();

		rewardService.createReward(rewardDTO, userId);
	}
}
