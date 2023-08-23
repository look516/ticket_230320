package com.ticket.booking.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ticket.booking.domain.Booking;

@Repository
public interface BookingMapper {
	public List<Booking> selectBookingListByUserId(int userId);
	
	public Booking selectBookingBybookingId(int bookingId);
	
	public List<Booking> selectBookingList(
			@Param("showId") int showId,
			@Param("selectedDate") String selectedDate,
			@Param("selectedTime") String selectedTime);
	
	// 한 번에 넣는 법?
	public Integer insertBooking(
			Map<String, Object> bookingMap);
	
}
