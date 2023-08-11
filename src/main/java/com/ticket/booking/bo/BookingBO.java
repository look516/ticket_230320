package com.ticket.booking.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.booking.dao.BookingMapper;
import com.ticket.booking.domain.Booking;

@Service
public class BookingBO {
	@Autowired
	private BookingMapper bookingMapper;
	
	public List<Booking> getBookingListByUserId(int userId) {
		return bookingMapper.selectBookingListByUserId(userId);
	}
}
