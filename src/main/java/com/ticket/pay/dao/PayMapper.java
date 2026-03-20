package com.ticket.pay.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ticket.pay.domain.Pay;

@Mapper
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
