package com.fund.fundingmate;

import com.fund.fundingmate.domain.investment.dto.InvestmentDTO;
import com.fund.fundingmate.domain.investment.repository.InvestmentRepository;
import com.fund.fundingmate.domain.investment.service.InvestmentService;
import com.fund.fundingmate.domain.payment.dto.InvestPeopleDTO;
import com.fund.fundingmate.domain.payment.dto.PaymentDTO;
import com.fund.fundingmate.domain.payment.repository.InvestPeopleRepository;
import com.fund.fundingmate.domain.payment.repository.PaymentRepository;
import com.fund.fundingmate.domain.payment.service.InvestPeopleService;
import com.fund.fundingmate.domain.payment.service.PaymentService;
import com.fund.fundingmate.domain.reward.dto.*;
import com.fund.fundingmate.domain.investment.dto.*;
import com.fund.fundingmate.domain.reward.repository.RewardCommentRepository;
import com.fund.fundingmate.domain.reward.repository.RewardRepository;
import com.fund.fundingmate.domain.reward.service.RewardCommentService;
import com.fund.fundingmate.domain.reward.service.RewardService;
import com.fund.fundingmate.domain.user.repository.UserRepository;
import com.fund.fundingmate.domain.user.entity.User;
import com.fund.fundingmate.global.file.Service.FileService;
import com.fund.fundingmate.global.file.dto.FileDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;

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

	@Autowired
	private RewardCommentRepository rewardCommentRepository;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private InvestmentService investmentService;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private InvestPeopleRepository investPeopleRepository;

	@Autowired
	private InvestmentRepository investmentRepository;

	@Autowired
	private FileService fileService;

	@Autowired
	private ModelMapper modelMapper;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void insertMember() {
		User user = new User();

		user.setBirthday("2000/11/12");
		user.setEmail("sally1112@naver.com");
		user.setUserid("sally1112");
		user.setName("장채윤");
		user.setNotificationStatus("N");
		user.setPassword("123421");
		user.setTel("010-1233-2456");
		user.setVitalization(1);

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
		FileDTO fileDTO = new FileDTO();
		rewardDTO.setProjName("500병 한정! 와인을 증류하고 오크숙성으로 완성하다! '바야흐로'");
		rewardDTO.setProjTargetAmount(100000);
		rewardDTO.setProjDateStart(LocalDate.of(2023, 6, 25));
		rewardDTO.setProjDateEnd(LocalDate.of(2023, 7, 16));
		rewardDTO.setProjKeyword("푸드");
		rewardDTO.setRewardVideoAddress("https://www.youtube.com/watch?v=9T8aX98xWAc");
		rewardDTO.setProjContent("충북 영동에 위치한 시나브로 와이너리는 한국와인의 명가로 널리 알려져 있습니다.\n" +
				"시나브로 와이너리에서 양조한 와인은 독일 베를린 와인 트로피, 대한민국 우리술 품평회 등에서 인정받았습니다. 세계에서 인정하는 와인을 증류하고 오크숙성을 통해 만들어낸 술이 바로 '바야흐로 오크' 입니다.");
		rewardDTO.setRewardRefundExchangePolicy("다음과 같은 사항 시 교환 및 환불이 안됩니다.\n" +
				"-참여자의 책임 있는 사유로 리워드가 멸실/훼손된 경우(단지 확인을 위한 포장 훼손 제외)\n" +
				"-참여자의 사용/소비에 의해 리워드의 가치가 감소한 경우\n" +
				"-시간 경과로 인해 재판매가 곤란할 정도로 리워드 가치가 상실한 경우\n" +
				"-참여자의 단순 변심\n" +
				"-진행자를 통환 교환/환불 접수 절차 없이 임의로 반송한 경우\n" +
				"-택배 배송중 파손된 건에 대해서는 교환 및 환불 가능합니다.\n" +
				"문의처 : 0437425275");
		rewardDTO.setRewardContact("043-742-5275");
		rewardDTO.setRewardEmail("sinabro_wine@naver.com");
		rewardDTO.setRewardCategory("푸드");
		rewardDTO.setModelName("과실주");
		rewardDTO.setCountryOfOrigin("이근용/충북 영동군 심천면 약목2길 23");
		rewardDTO.setManufacturer("불휘농장");
		rewardDTO.setRewardLaw("해당사항없음");
		rewardDTO.setAsPhoneNumber("043-742-5275");
		rewardDTO.setBusinessAddress("충청북도 영동군 심천면 약목2길 23");
		rewardDTO.setBank("농협은행");
		rewardDTO.setAccNumber("1234-5678-9012-3456");
		rewardDTO.setDepositorName("이근용");



		rewardDTO.setTaxBillEmail("sinabro_wine@naver.com");
		rewardDTO.setWebsiteUrl("https://bhwinery.com");
		rewardDTO.setFacebookUrl(null);
		rewardDTO.setInstagramUrl("https://www.instagram.com/sinabro_winery/");
		rewardDTO.setBlogUrl(null);
		rewardDTO.setTwitterUrl(null);

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

		MultipartFile repFile = new MockMultipartFile("rewardprj1.png", "rewardprj1.png", "image/png", new byte[0]);
		MultipartFile conFile = new MockMultipartFile("rewardprj1-2.png", "rewardprj1-2.png", "image/png", new byte[0]);
		MultipartFile businessImg = new MockMultipartFile("business-license-receipt.jpg", "business-license-receipt.jpg", "image/jpg", new byte[0]);
		MultipartFile bankImg = new MockMultipartFile("bank_account_copy_image.jpg", "bank_account_copy_image.jpg", "image/jpg", new byte[0]);

		try {
			// Save each file and set the corresponding field in the rewardDTO
			com.fund.fundingmate.global.file.entity.File savedRepFile = fileService.saveFile(null, repFile);
			rewardDTO.setRepFile(modelMapper.map(savedRepFile, FileDTO.class));

			com.fund.fundingmate.global.file.entity.File savedConFile = fileService.saveFile(null, conFile);
			rewardDTO.setConFile(modelMapper.map(savedConFile, FileDTO.class));

			com.fund.fundingmate.global.file.entity.File savedBusinessImg = fileService.saveFile(null, businessImg);
			rewardDTO.setBusinessImg(modelMapper.map(savedBusinessImg, FileDTO.class));

			com.fund.fundingmate.global.file.entity.File savedBankImg = fileService.saveFile(null, bankImg);
			rewardDTO.setBankImg(modelMapper.map(savedBankImg, FileDTO.class));

			rewardDTO.getRepFile().setFileName(repFile.getOriginalFilename());
			rewardDTO.getConFile().setFileName(conFile.getOriginalFilename());
			rewardDTO.getBusinessImg().setFileName(businessImg.getOriginalFilename());
			rewardDTO.getBankImg().setFileName(bankImg.getOriginalFilename());

		} catch (IOException e) {
			e.printStackTrace();
		}

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
		Long rewardId = 1L;
		Long commentId = 1L;

		RewardReplyDTO rewardReplyDTO = new RewardReplyDTO();
		rewardReplyDTO.setRepContent("My Reply Content");
		rewardReplyDTO.setRewardId(rewardId);
		rewardReplyDTO.setCommentId(commentId);

		rewardCommentService.insertRewardCommentReply(rewardReplyDTO);
	}

	@Test
	void insertPayment() {
		Long targetUserId = 1L;

		Optional<User> userOptional = userRepository.findById(targetUserId);
		if (userOptional.isEmpty()) {
			System.out.println("User not found with ID: " + targetUserId);
			return;
		}

		User user = userOptional.get();

		PaymentDTO paymentDTO = new PaymentDTO();
		paymentDTO.setUser(user);
		paymentDTO.setCardnumber("0123456789012345");
		paymentDTO.setCardpassword("8765");
		paymentDTO.setPaymentcode(true);
		paymentDTO.setPaymentamount(12345678);
		paymentDTO.setPayenddate("1123");
		paymentDTO.setBirthday("001112");
		paymentDTO.setPayperiod("일시불");
		paymentDTO.setShippingadress("경기도 부천시 상2동 569-3");
		paymentDTO.setShippingadressdesc("3층");

		paymentService.createPayment(paymentDTO);
	}

	@Test
	void insertInvestPeople() {
		Long targetUserId = 1L;

		Optional<User> userOptional = userRepository.findById(targetUserId);
		if (userOptional.isEmpty()) {
			System.out.println("User not found with ID: " + targetUserId);
			return;
		}

		User user = userOptional.get();

		InvestPeopleDTO investPeopleDTO = new InvestPeopleDTO();
		investPeopleDTO.setUser(user);
		investPeopleDTO.setName("장채리");
		investPeopleDTO.setSecuritynumber1("001112");
		investPeopleDTO.setSecuritynumber2("4789535");
		investPeopleDTO.setCalltype("KT");
		investPeopleDTO.setCallnumber("01078965841");

		InvestPeopleService investPeopleService = new InvestPeopleService(investPeopleRepository);
		investPeopleService.createInvestPeople(investPeopleDTO);
	}

	@Test
	void selectRewardById() {
		Long rewardId = 1L;

		try {
			Map<String, Object> rewardMap = rewardService.getRewardById(rewardId);
			RewardDTO rewardDTO = (RewardDTO) rewardMap.get("reward");
			System.out.println(rewardDTO.toString());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	void findByProjDateEndBefore() {
		try {
			List<RewardDTO> rewards = rewardService.getRewardWithProjDateEndBeforeToday();
			System.out.println(rewards.toString());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	void findByProjDateEndAfter() {
		try {
			List<RewardDTO> rewards = rewardService.getRewardWithProjDateEndBefore();
			System.out.println(rewards.toString());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	void findByProjStartAfter() {
		try {
			List<RewardDTO> rewards = rewardService.getRewardWithProjDateStartAfter();
			System.out.println(rewards.toString());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void insertInvest(){

		Long targetUserId = 1L;

		Optional<User> userOptional = userRepository.findById(targetUserId);
		if(userOptional.isEmpty()) {
			System.out.println("User not found with ID: " + targetUserId);
			return;
		}


		User user = userOptional.get();

		InvestmentDTO investmentDto = new InvestmentDTO();
		FileDTO fileDTO = new FileDTO();

		investmentDto.setInvestCategory("테스트 카테고리");
		investmentDto.setInvestProjName("테스트 프로젝트");
		investmentDto.setInvestTargetAmount(10000);
		investmentDto.setInvestProjDateStart(new Date());
		investmentDto.setInvestProjDateEnd(new Date());
	//	investmentDto.setInvestRepImgSavedName("테스트 이미지.jpg");
		investmentDto.setInvestProjKeyword("테스트 키워드");
		investmentDto.setUseOfFunds("테스트 자금 사용");
		investmentDto.setUseOfFundsDateStart(new Date());
		investmentDto.setUseOfFundsDateEnd(new Date());
		investmentDto.setRateOfReturn(5);
		investmentDto.setExpectedPaymentDate(new Date());
		investmentDto.setRepaymentMethod("테스트 상환 방법");
		investmentDto.setInvestVideoUrl("테스트 비디오 URL");
	//	investmentDto.setInvestContentImgSavedName("테스트 내용 이미지.jpg");
		investmentDto.setInvestItemIntro("테스트 투자 항목 소개");
		investmentDto.setInvestItemBusinessValue("테스트 투자 항목 비즈니스 가치");
		investmentDto.setInvestItemValue("테스트 투자 항목 가치");
		investmentDto.setInvestItemBenefit("테스트 투자 항목 혜택");
		investmentDto.setInvestProjContent("테스트 프로젝트 내용");
	//	investmentDto.setInvestIdBusinessLicenseImgSavedName("테스트 사업자 등록증 이미지.jpg");
		investmentDto.setBusinessAddress("테스트 사업장 주소");
		investmentDto.setInvestEmail("test@example.com");
		investmentDto.setBank("테스트 은행");
		investmentDto.setAccNumber("1234567890");
		investmentDto.setDepositorName("테스트 예금주명");
	//	investmentDto.setInvestBankAccountCopyImgSavedName("테스트 계좌 복사 이미지.jpg");
		investmentDto.setTaxBillEmail("test@example.com");
		investmentDto.setWebsiteUrl("테스트 웹사이트 URL");
		investmentDto.setFacebookUrl("테스트 페이스북 URL");
		investmentDto.setInstagramUrl("테스트 인스타그램 URL");
		investmentDto.setBlogUrl("테스트 블로그 URL");
		investmentDto.setTwitterUrl("테스트 트위터 URL");


		// Create reward type DTOs
		List<InvestTypeDTO> investTypeDTOs = new ArrayList<>();
		InvestTypeDTO investTypeDTO = new InvestTypeDTO();
		investTypeDTO.setInvestAmount(500);
		investTypeDTO.setInvestLimit(true);
		investTypeDTO.setInvestLimitCount(5);

		// Set other properties of the rewardTypeDTO


		investTypeDTOs.add(investTypeDTO);
		investmentDto.setInvestTypes(investTypeDTOs);


		MultipartFile investRepImgSavedName = new MockMultipartFile("rewardprj1.png", "rewardprj1.png", "image/png", new byte[0]);
		MultipartFile investContentImgSavedName = new MockMultipartFile("rewardprj1-2.png", "rewardprj1-2.png", "image/png", new byte[0]);
		MultipartFile investIdBusinessLicenseImgSavedName = new MockMultipartFile("business-license-receipt.jpg", "business-license-receipt.jpg", "image/jpg", new byte[0]);
		MultipartFile investBankAccountCopyImgSavedName = new MockMultipartFile("bank_account_copy_image.jpg", "bank_account_copy_image.jpg", "image/jpg", new byte[0]);

		try {
			// Save each file and set the corresponding field in the rewardDTO
			com.fund.fundingmate.global.file.entity.File savedInvestRepImgSavedName = fileService.saveFile(null, investRepImgSavedName);
			investmentDto.setInvestRepImgSavedName(modelMapper.map(savedInvestRepImgSavedName, FileDTO.class));

			com.fund.fundingmate.global.file.entity.File savedInvestContentImgSavedName = fileService.saveFile(null, investContentImgSavedName);
			investmentDto.setInvestContentImgSavedName(modelMapper.map(savedInvestContentImgSavedName, FileDTO.class));

			com.fund.fundingmate.global.file.entity.File savedInvestIdBusinessLicenseImgSavedName = fileService.saveFile(null, investIdBusinessLicenseImgSavedName);
			investmentDto.setInvestIdBusinessLicenseImgSavedName(modelMapper.map(savedInvestIdBusinessLicenseImgSavedName, FileDTO.class));

			com.fund.fundingmate.global.file.entity.File savedInvestBankAccountCopyImgSavedName = fileService.saveFile(null, investBankAccountCopyImgSavedName);
			investmentDto.setInvestBankAccountCopyImgSavedName(modelMapper.map(savedInvestBankAccountCopyImgSavedName, FileDTO.class));

			investmentDto.getInvestRepImgSavedName().setFileName(investRepImgSavedName.getOriginalFilename());
			investmentDto.getInvestContentImgSavedName().setFileName(investContentImgSavedName.getOriginalFilename());
			investmentDto.getInvestIdBusinessLicenseImgSavedName().setFileName(investIdBusinessLicenseImgSavedName.getOriginalFilename());
			investmentDto.getInvestBankAccountCopyImgSavedName().setFileName(investBankAccountCopyImgSavedName.getOriginalFilename());

		} catch (IOException e) {
			e.printStackTrace();
		}

		Long userId = user.getId();

		investmentService.createInvestment(investmentDto, userId);
	}

}
