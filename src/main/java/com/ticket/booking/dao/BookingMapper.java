package com.ticket.booking.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ticket.booking.domain.Booking;

@Repository
public interface BookingMapper {
	public List<Booking> selectBookingListByUserId(int userId);
}
