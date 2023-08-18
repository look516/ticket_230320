package com.ticket.booking.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ticket.booking.domain.Booking;
import com.ticket.booking.domain.BookingView;

@Repository
public interface BookingMapper {
	public List<Booking> selectBookingListByUserId(int userId);
	
	public Booking selectBookingBybookingId(int bookingId);
}
