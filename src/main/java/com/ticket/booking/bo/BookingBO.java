package com.ticket.booking.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.booking.dao.BookingMapper;
import com.ticket.booking.domain.Booking;
import com.ticket.booking.domain.BookingView;
import com.ticket.show.bo.ShowBO;
import com.ticket.show.domain.ShowView;
import com.ticket.user.Entity.UserEntity;
import com.ticket.user.bo.UserBO;

@Service
public class BookingBO {
	@Autowired
	private BookingMapper bookingMapper;
	
	@Autowired
	private ShowBO showBO;
	
	@Autowired
	private UserBO userBO;
	
	public List<Booking> getBookingListByUserId(int userId) {
		return bookingMapper.selectBookingListByUserId(userId);
	}
	
	public BookingView generateBookingViewBybookingId(int bookingId) {
		BookingView bookingView = new BookingView();
		
		// booking
		Booking booking = bookingMapper.selectBookingBybookingId(bookingId);
		
		// showView
		ShowView show = showBO.generateShowViewByShowId(booking.getShowId());
		
		// userEntity
		UserEntity user = userBO.getUserEntityById(booking.getUserId());
		
		bookingView.setUser(user);
		bookingView.setBooking(booking);
		bookingView.setShowView(show);
		
		return bookingView;
		
	}
}
