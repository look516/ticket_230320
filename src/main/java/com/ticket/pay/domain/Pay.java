package com.ticket.pay.domain;

import java.time.ZonedDateTime;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Pay {
	private int id;
	private int bookingId;
	private String payNumber;
	private String payment;
	private Date payDate;
	private String discountName;
	private int discountPrice;
	private int isValid;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
}
