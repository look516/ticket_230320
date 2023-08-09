package com.ticket.theater.bo;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TheaterBOTest {
	
	@Autowired
	TheaterBO theaterBO;

	//@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Test
	void 공연장가져오기() {
		theaterBO.getTheaterEntityById(1);
	}

}
