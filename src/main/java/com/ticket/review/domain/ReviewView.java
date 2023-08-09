package com.ticket.review.domain;

import com.ticket.review.entity.ReviewEntity;
import com.ticket.user.Entity.UserEntity;

import lombok.Data;

@Data
public class ReviewView {
	private ReviewEntity review;
	
	private UserEntity user;
}
