package com.ticket.booking.domain;

import com.ticket.show.domain.ShowView;
import com.ticket.user.Entity.UserEntity;

import lombok.Data;

@Data
public class BookingView {
	private ShowView showView;
	
	private Booking booking;
	
	private UserEntity user;
}
