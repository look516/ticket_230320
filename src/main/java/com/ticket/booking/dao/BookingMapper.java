package com.ticket.booking.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import com.ticket.booking.domain.Booking;

@Mapper
public interface BookingMapper {
	public List<Booking> selectBookingListByUserId(
			@Param("userId") int userId,
			@Param("isBooked") Integer isBooked);
	
	public Booking selectBookingBybookingId(int bookingId);
	
	public List<Booking> selectBookingList(
			@Param("showId") int showId,
			@Param("selectedDate") String selectedDate,
			@Param("selectedTime") String selectedTime);
	
	// 한 번에 넣는 법?
	public Integer insertBooking(
			Map<String, Object> bookingMap);
	
	public void updateBookingById(int bookingId);
	
}
