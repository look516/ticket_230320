package com.ticket.show.domain;

import java.util.List;

import com.ticket.review.domain.ReviewView;
import com.ticket.show.entity.ShowEntity;
import com.ticket.theater.entity.TheaterEntity;

import lombok.Data;

@Data
public class ShowView {
	// 공연 한 개
	private ShowEntity show;
	
	// 장소
	private TheaterEntity theater;
	
	// 후기들
	private List<ReviewView> reviewList;
}
