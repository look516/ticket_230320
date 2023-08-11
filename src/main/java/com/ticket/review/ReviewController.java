package com.ticket.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ticket.show.bo.ShowBO;
import com.ticket.show.entity.ShowEntity;

@Controller
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	private ShowBO showBO;
	
	// 로그인 여부 임시적으로 처리 필요?
	@GetMapping("/review_create_view")
	public String reviewCreateView(
			@RequestParam("showId") int showId,
			Model model) {
		
		// 1) model에 showView 넘기기
		// 2) model에 id name - entity로 넘기기
		
		ShowEntity show = showBO.getShowNameById(showId);
		
		model.addAttribute("show", show);
		model.addAttribute("view", "review/reviewCreate");
		return "template/layout";
	}
}
