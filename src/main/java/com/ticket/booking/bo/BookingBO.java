package com.ticket.booking.bo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.booking.dao.BookingMapper;
import com.ticket.booking.domain.Booking;
import com.ticket.booking.domain.BookingInfo;
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
	
	public List<String> getBookingList(int showId, String selectedDate, String selectedTime) {
		
		//조건에 해당하는 booking list를 불러온다.
		List<Booking> bookingList = bookingMapper.selectBookingList(showId, selectedDate, selectedTime);
		
		List<String> seatNums = new ArrayList<>();
		//booking별 좌석을 추출한다.
		for (int i = 0; i < bookingList.size(); i++) {
			String seatNum = bookingList.get(i).getSeat().split(" ")[3];
			seatNums.add(i, seatNum);
		}
		
		return seatNums;
	}
	
	
	
	
	
	
	
	public Integer addBooking(int userId, BookingInfo bookingInfo) {
			
			
		// showId, showDate, showTime, seat, seatGrade
		// userId(session), bookingNumber(random), bookingDate(new Date), seatPrice(seat관련), isReserved(1로 두고 이면 cancel됐을 때 0으로 업데이트)
		
		int showId = bookingInfo.getShowId();
		String showDate = bookingInfo.getShowDate();
		String showTime = bookingInfo.getShowTime();
		String seatGrade = bookingInfo.getSeatGradeInput();
		String seat = bookingInfo.getSeatInput();
				
				
				
				
				
		// bookingNumber는 중복되지 않는 난수
		
		// 중복되지 않는(ajax) 난수(bo)를 설정한다.
		Random random = new Random();
		
		int min = 1;
        int max = 99999999;
        int randomNum = random.nextInt(max - min + 1) + min;

        // 추후 랜덤 숫자 + 문자가 되도록
        String bookingNumber = randomNum + "a";
        
        // 중복 걸러지는지 테스트 String bookingNumber = "12345678";
		
        // pay에서도 seatPrice를 정의하므로 추후 거기서 받아오는 코드로 변경
        int seatPrice = 0;
		if (seatGrade.equals("R")) {
			seatPrice = 60000;
		}
		
		//seat = seat.substring(6);
		
		
		Map<String, Object> bookingMap = new HashMap<>();
		bookingMap.put("userId", userId);
		bookingMap.put("showId", showId);
		bookingMap.put("showDate", showDate);
		bookingMap.put("showTime", showTime);
		bookingMap.put("seatGrade", seatGrade);
		bookingMap.put("seat", seat);
		bookingMap.put("bookingNumber", bookingNumber);
		bookingMap.put("seatPrice", seatPrice);
		
		
		
		
		
		
		
		
		
		
		bookingMapper.insertBooking(bookingMap);
		BigInteger bigInteger = (BigInteger) bookingMap.get("id");
		return bigInteger.intValue();
	}
	
	
	
	public void updateBooking(int bookingId) {
		bookingMapper.updateBookingById(bookingId);
	}
}