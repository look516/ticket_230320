package com.ticket.review.bo;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.review.dao.ReviewMapper;
import com.ticket.review.dao.ReviewRepository;
import com.ticket.review.domain.ReviewView;
import com.ticket.review.entity.ReviewEntity;
import com.ticket.user.Entity.UserEntity;
import com.ticket.user.bo.UserBO;

@Service
public class ReviewBO {
	
	// logger
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private ReviewMapper reviewMapper;
	
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
	
	
	
	public int addReview(int showId, int userId, String subject, String content, int point, String showDate, String showTime) {
		// String to time / Date
		/*if (showTime == null | showTime == "") {
			// time은 non-nullable 이므로 임시적으로 00:00:00을 넣어준다.
			showTime = "00:00:00";
		}
		Time realShowTime = Time.valueOf(showTime);*/
		
		SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");
		
		Date realShowDate = null;
		try {
			realShowDate = fmt.parse(showDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return reviewMapper.insertReview(showId, userId, subject, content, point, realShowDate, showTime);
	}
}
