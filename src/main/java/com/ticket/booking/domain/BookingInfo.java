package com.ticket.booking.domain;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class BookingInfo {
	//private int id;
	private int showId;
	//private int userId;
	//private String bookingNumber;
	//private Date bookingDate;
	private String showDate;
	private String showTime;
	private String seatInput;
	//private int seatPrice;
	private String seatGradeInput;
	private int totalInput;
	//private int isReserved;
	//private ZonedDateTime createdAt;
	//private ZonedDateTime updatedAt;
}
