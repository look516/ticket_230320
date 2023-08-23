package com.ticket.pay.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ticket.pay.domain.Pay;

@Repository
public interface PayMapper {
	public Integer insertPay(
			@Param("bookingId") int bookingId,
			@Param("payNumber") String payNumber,
			@Param("payment") String payment,
			@Param("discountName") String discountName,
			@Param("discount") int discount);
	
	public Pay selectPay(int bookingId);
	
	public void updatePayById(int payId);
				
}
