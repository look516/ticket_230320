package com.ticket.review.domain;

import java.sql.Date;
import java.sql.Time;
import java.time.ZonedDateTime;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Review {
		private int id;
		private int showId;
		private int userId;
		private String subject;
		private String content;
		private int point;
		private Date showDate;
		private Time showTime;
		private ZonedDateTime createdAt;		
		private ZonedDateTime updatedAt;
}
