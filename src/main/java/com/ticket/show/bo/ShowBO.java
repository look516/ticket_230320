package com.ticket.show.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ticket.review.bo.ReviewBO;
import com.ticket.review.domain.ReviewView;
import com.ticket.show.dao.ShowMapper;
import com.ticket.show.dao.ShowRepository;
import com.ticket.show.domain.ShowView;
import com.ticket.show.entity.ShowEntity;
import com.ticket.theater.bo.TheaterBO;
import com.ticket.theater.entity.TheaterEntity;

@Service
public class ShowBO {
	@Autowired
	private ShowMapper showMapper; // mybatis
	
	@Autowired
	private ShowRepository showRepository; // JPA
	
	
	@Autowired
	private TheaterBO theaterBO;
	
	@Autowired
	private ReviewBO reviewBO;
	
	
	
	public ShowView generateShowViewByShowId(int showId) {
		ShowView showView = new ShowView();
		
		// 공연 한 개
		ShowEntity show = showRepository.findById(showId);
		showView.setShow(show);
		
		// 장소
		showView.setTheater(theaterBO.getTheaterEntityById(show.getTheaterId()));
		
		// 리뷰들
		List<ReviewView> reviewViewList = reviewBO.generateReviewViewList(show.getId());
		showView.setReviewList(reviewViewList);
		
		return showView;
	}
	
	public List<ShowView> generateShowViewList(String genre, Pageable pageable, Model model, String search) {
		
		List<ShowView> showViewList = new ArrayList<>();
		
		Page<ShowEntity> showList;
		
		
		// mybatis로 처리하고 싶다
		// 한 번에 처리할 수는 없을까
		if (search == null && genre.equals("전체")) {
			showList = showRepository.findAllByOrderByIdDesc(pageable);
		} else if (search == null){
			showList = showRepository.findByGenreOrderByIdDesc(genre, pageable);
		} else {
			showList = showRepository.findByNameContaining(search, pageable);
		}
		
		for (ShowEntity show : showList) {
			// showView 하나 형성
			ShowView showView = new ShowView();
			
			// show entity 집어넣기
			showView.setShow(show);
			
			// theater entity에 select 해온 것 집어넣기
			int theaterId = show.getTheaterId();
			TheaterEntity theater = theaterBO.getTheaterEntityById(theaterId);
			showView.setTheater(theater);
			
			
			
			// n번째 showView가 된다
			showViewList.add(showView);
		}
		
		model.addAttribute("totalPages", showList.getTotalPages());
	    model.addAttribute("currentPage", showList.getNumber());
	    
		return showViewList;
	}
	
	
	
	
	public ShowEntity getShowNameById(int showId) {
		ShowEntity show = showRepository.findById(showId);
		return show;
	}
}
