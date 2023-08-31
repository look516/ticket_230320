package com.ticket.show.bo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ticket.show.domain.ShowView;

@SpringBootTest
class ShowBOTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ShowBO showBO;
	
	
	
	//@Test
	void test() {
		fail("Not yet implemented");
	}
	
	/*
	@Test
	void 공연목록가져오기() {
		List<ShowView> show = showBO.generateShowViewList("뮤지컬");
		logger.info("########## show: {}", show.get(0));
		assertNotNull(show);
	}
	*/
	
	
}
