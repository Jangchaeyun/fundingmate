package com.fund.fundingmate;

import com.fund.fundingmate.domain.user.repository.UserRepository;
import com.fund.fundingmate.domain.user.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FundingmateApplicationTests {
	@Autowired
	private UserRepository userRepository;

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
}
