package com.ticket.review.dao;

import java.sql.Time;
import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewMapper {
	public int insertReview(
			@Param("showId") int showId,
			@Param("userId") int userId,
			@Param("subject") String subject,
			@Param("content") String content,
			@Param("point") int point,
			@Param("showDate") Date showDate,
			@Param("showTime") String showTime);
	
	public double selectReviewAveragePoint(int showId);
	
}
