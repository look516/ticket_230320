package com.ticket.show.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ticket.review.bo.ReviewBO;
import com.ticket.review.domain.ReviewView;
import com.ticket.show.dao.ShowRepository;
import com.ticket.show.domain.ShowView;
import com.ticket.show.entity.ShowEntity;
import com.ticket.theater.bo.TheaterBO;
import com.ticket.theater.entity.TheaterEntity;

@Service
public class ShowBO {
	@Autowired
	private ShowRepository showRepository;
	
	@Autowired
	private TheaterBO theaterBO;
	
	@Autowired
	private ReviewBO reviewBO;
	
	public ShowView generateShowViewByShowId(int showId) {
		ShowView showView = new ShowView();
		
		ShowEntity show = showRepository.findById(showId)
				.orElseThrow(() -> new IllegalArgumentException("공연을 찾을 수 없습니다. id=" + showId));
		showView.setShow(show);
		showView.setTheater(theaterBO.getTheaterEntityById(show.getTheaterId()));
		
		List<ReviewView> reviewViewList = reviewBO.generateReviewViewList(show.getId());
		showView.setReviewList(reviewViewList);
		
		return showView;
	}
	
	public List<ShowView> generateShowViewList(String genre, Pageable pageable, Model model, String search) {
		List<ShowView> showViewList = new ArrayList<>();
		
		Page<ShowEntity> showPage;
		if (search == null && genre.equals("전체")) {
			showPage = showRepository.findAllByOrderByIdDesc(pageable);
		} else if (search == null) {
			showPage = showRepository.findByGenreOrderByIdDesc(genre, pageable);
		} else {
			showPage = showRepository.findByNameContaining(search, pageable);
		}
		
		for (ShowEntity show : showPage) {
			ShowView showView = new ShowView();
			showView.setShow(show);
			TheaterEntity theater = theaterBO.getTheaterEntityById(show.getTheaterId());
			showView.setTheater(theater);
			showViewList.add(showView);
		}
		
		model.addAttribute("totalPages", showPage.getTotalPages());
		model.addAttribute("currentPage", showPage.getNumber());
		
		return showViewList;
	}
	
	public ShowEntity getShowNameById(int showId) {
		return showRepository.findById(showId).orElse(null);
	}

	public Map<String, Object> getShowListResult(String genre, Pageable pageable, String search) {
		Page<ShowEntity> showPage;
		if (search == null && "전체".equals(genre)) {
			showPage = showRepository.findAllByOrderByIdDesc(pageable);
		} else if (search == null) {
			showPage = showRepository.findByGenreOrderByIdDesc(genre, pageable);
		} else {
			showPage = showRepository.findByNameContaining(search, pageable);
		}

		List<ShowView> showViewList = new ArrayList<>();
		for (ShowEntity show : showPage) {
			ShowView showView = new ShowView();
			showView.setShow(show);
			showView.setTheater(theaterBO.getTheaterEntityById(show.getTheaterId()));
			showViewList.add(showView);
		}

		Map<String, Object> result = new HashMap<>();
		result.put("showList", showViewList);
		result.put("currentPage", showPage.getNumber());
		result.put("totalPages", showPage.getTotalPages());
		return result;
	}
}
