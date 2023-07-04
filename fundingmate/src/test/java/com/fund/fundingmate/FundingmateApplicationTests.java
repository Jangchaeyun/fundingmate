package com.fund.fundingmate;

import com.fund.fundingmate.domain.reward.dto.*;
import com.fund.fundingmate.domain.reward.entity.Reward;
import com.fund.fundingmate.domain.reward.entity.RewardComment;
import com.fund.fundingmate.domain.reward.entity.RewardReply;
import com.fund.fundingmate.domain.reward.repository.RewardCommentRepository;
import com.fund.fundingmate.domain.reward.repository.RewardRepository;
import com.fund.fundingmate.domain.reward.service.RewardCommentService;
import com.fund.fundingmate.domain.reward.service.RewardService;
import com.fund.fundingmate.domain.user.dto.UserDTO;
import com.fund.fundingmate.domain.user.repository.UserRepository;
import com.fund.fundingmate.domain.user.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FundingmateApplicationTests {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RewardService rewardService;

	@Autowired
	private RewardCommentService rewardCommentService;

	@Autowired
	private RewardRepository rewardRepository;

	@Test
	void insertMember() {
		User user = new User();

		user.setBirthday("1998/11/14");
		user.setEmail("hee1124@naver.com");
		user.setUserid("hee1124");
		user.setName("김윤희");
		user.setNotificationStatus("Y");
		user.setPassword("675923");
		user.setTel("010-3478-5157");
		user.setVitalization(0);

		userRepository.save(user);
	}

	@Test
	void insertReward() {
		Long targetUserId = 1L;

		Optional<User> userOptional = userRepository.findById(targetUserId);
		if(userOptional.isEmpty()) {
			System.out.println("User not found with ID: " + targetUserId);
			return;
		}

		User user = userOptional.get();

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

		Long userId = user.getId();

		rewardService.createReward(rewardDTO, userId);
	}

	@Test
	void insertRewardComment() {
		Long rewardId = 1L;
		Long userId = 1L;

		Optional<User> userOptional = userRepository.findById(userId);
		if (userOptional.isEmpty()) {
			System.out.println("User not found with ID: " + userId);
			return;
		}

		User user = userOptional.get();

		RewardCommentDTO rewardCommentDTO = new RewardCommentDTO();
		rewardCommentDTO.setComTitle("My Comment Title");
		rewardCommentDTO.setComContent("My Comment Content");

		RewardDTO rewardDTO = new RewardDTO();
		rewardDTO.setId(rewardId);

		rewardCommentDTO.setReward(rewardDTO);
		rewardCommentDTO.setUser(user.toDTO());

		rewardCommentService.insertRewardComment(rewardCommentDTO);
	}

	@Test
	void insertRewardCommentReply() {
	}
}
