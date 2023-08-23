package com.ticket.pay.bo;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.pay.dao.PayMapper;
import com.ticket.pay.domain.Pay;

@Service
public class PayBO {
	
	@Autowired
	private PayMapper payMapper;
	
	
	
	public Integer addPay(int bookingId, String discount, String payment, String discountName) {
		// payNumber는 중복되지 않는 난수
		// unique key로 설정
		// 중복되지 않는 난수를 설정한다.
		Random random = new Random();
		
		int min = 1;
        int max = 99999999;
        int randomNum = random.nextInt(max - min + 1) + min;

        // 추후 랜덤 숫자 + 문자가 되도록
        String payNumber = randomNum + "b";
        
        // 중복 걸러지는지 테스트
        
        
        // 추후 60000원 이 아니라 다른 값이면 절삭하는 로직
        double discountDouble = Double.parseDouble(discount);
        int discountInt = (int)discountDouble;
		
		return payMapper.insertPay(bookingId, payNumber, payment, discountName, discountInt);
	}
	
	
	
	
	public Pay getPay(int bookingId) {
		return payMapper.selectPay(bookingId);
	}
	
	public void updatePay(int payId) {
		payMapper.updatePayById(payId);
	}
}
