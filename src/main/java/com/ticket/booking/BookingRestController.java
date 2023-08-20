package com.ticket.booking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.booking.bo.BookingBO;

@RequestMapping("/book")
@RestController
public class BookingRestController {
	
	@Autowired
	private BookingBO bookingBO;
	
	@GetMapping("/booking_seat")
	public List<String> bookingSeat(
			@RequestParam("showId") int showId,
			@RequestParam("selectedDate") String selectedDate,
			@RequestParam("selectedTime") String selectedTime) {
		List<String> selectedSeatNums = bookingBO.getBookingList(showId, selectedDate, selectedTime);
		
		return selectedSeatNums;
	}
	
	// REQUEST MAP으로 묶어서 보내는 방법도 추후 적용해보자.
	@PostMapping("/booking")
	public ResponseEntity<String> booking(
			@RequestParam("showId") int showId,
			@RequestParam("showDate") String showDate,
			@RequestParam("showTime") String showTime,
			@RequestParam("totalInput") String totalInput,
			@RequestParam("seatGradeInput") String seatGrade,
			@RequestParam("seatInput") String seat,
			HttpSession session) {
		
		int userId = (int)session.getAttribute("userId");
		
		// db insert
		Integer bookingId = bookingBO.addBooking(userId, showId, showDate, showTime, seatGrade, seat);
		
		boolean isSuccess = (bookingId != null ? true : false);
		// 응답
		if (isSuccess) {
			return ResponseEntity.ok("Success");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed");
		}
	}
		

}
