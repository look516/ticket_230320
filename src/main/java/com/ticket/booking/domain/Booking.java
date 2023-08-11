package com.ticket.booking.domain;

import java.sql.Time;
import java.time.ZonedDateTime;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Booking {
	private int id;
	private int showId;
	private int userId;
	private String bookingNumber;
	private Date bookingDate;
	private Date showDate;
	private Time showTime;
	private String seat;
	private int seatPrice;
	private char seatGrade;
	private int isReserved;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
}
