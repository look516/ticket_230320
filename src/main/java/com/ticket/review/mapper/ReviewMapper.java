package com.ticket.review.mapper;

import java.util.Date;
import java.util.List;

import com.ticket.review.entity.ReviewEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
//@Repository
public interface ReviewMapper {
	public int insertReview(
			@Param("showId") int showId,
			@Param("userId") int userId,
			@Param("subject") String subject,
			@Param("content") String content,
			@Param("point") int point,
			@Param("showDate") Date showDate,
			@Param("showTime") String showTime);
	
	public Double selectReviewAveragePoint(int showId);
	
	public void deleteReviewById(int reviewId);

	public List<ReviewEntity> findByShowId(int showId);
	
}
