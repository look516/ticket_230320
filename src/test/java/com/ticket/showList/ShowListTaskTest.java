package com.ticket.showList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ticket.showList.bo.ShowListBO;

@SpringBootTest
class ShowListTaskTest {
	@Autowired
	private ShowListBO showListBO;
	
	@Test
	//@Scheduled(cron = "0 0 12 * * *") // 매일 12시마다
	public void DB에넣기() {
		// 함수 수행
		// 10개씩 1p~10p
		for (int i = 1; i <= 10; i ++) {
			// DB 저장
			showListBO.insertShow(i);
			// 3초 쉬기
			try {
				showListBO.wait(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
