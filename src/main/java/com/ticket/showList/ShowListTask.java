package com.ticket.showList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ticket.showList.bo.ShowListBO;

@Component
public class ShowListTask {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ShowListBO showListBO;
	
	@Scheduled(cron = "50 19 12 * * *") // 매일 12시마다
	public void insertShow() {
		// 함수 수행
		// 10개씩 1p~10p
		for (int i = 1; i <= 10; i ++) {
			// DB 저장
			showListBO.insertShow(i);
			// 3초 쉬기
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				logger.warn("###3초 쉬기 불가");
				e.printStackTrace();
			}
		}
	}
}
