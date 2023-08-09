package com.ticket.review.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.review.dao.ReviewRepository;
import com.ticket.review.domain.ReviewView;
import com.ticket.review.entity.ReviewEntity;
import com.ticket.user.Entity.UserEntity;
import com.ticket.user.bo.UserBO;

@Service
public class ReviewBO {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private UserBO userBO;
	
	public List<ReviewView> generateReviewViewList(int showId) {
		List<ReviewView> reviewViewList = new ArrayList<>();
		
		List<ReviewEntity> reviewList = reviewRepository.findByShowId(showId);
		
		for (ReviewEntity review : reviewList) {
			ReviewView reviewView = new ReviewView();
			reviewView.setReview(review);
			UserEntity user = userBO.getUserEntityById(review.getUserId());
			reviewView.setUser(user);
			
			reviewViewList.add(reviewView);
		}
		return reviewViewList;
	}
}
