package com.ticket.show.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ticket.show.entity.ShowEntity;

class ShowRepositoryTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ShowRepository showRepository;
	
	
	//@Test
	void test() {
		fail("Not yet implemented");
	}
	
	/*
	@Test
	void 공연목록가져오기() {
		List<ShowEntity> showList = showRepository.findAllByOrderByIdDesc();
		logger.info("########## showList: {}", showList);
		assertNotNull(showList);
	}
	*/

}
