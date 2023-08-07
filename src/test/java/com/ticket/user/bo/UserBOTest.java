package com.ticket.user.bo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ticket.user.Entity.UserEntity;

@SpringBootTest
class UserBOTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserBO userBO;
	
	
	//@Test
	void test() {
		fail("Not yet implemented");
	}

	//@Test
	void 회원조회() {
		UserEntity user = userBO.getUserEntityByLoginId("aaaa");
		logger.info("########## user: {}", user); // lombok에 의해 tostring override user 내용 보임
		assertNotNull(user);
	}
	
	@Transactional	// rollback
	@Test
	void 회원추가테스트() {
		userBO.addUser("abce", "abcd", "이름", "abcd@naver.com", "010-1111-1234");
	}
}
