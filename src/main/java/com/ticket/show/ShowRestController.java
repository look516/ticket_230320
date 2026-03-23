package com.ticket.show;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.review.bo.ReviewBO;
import com.ticket.show.bo.ShowBO;
import com.ticket.show.domain.ShowView;

@RequestMapping("/api")
@RestController
public class ShowRestController {

    @Autowired
    private ShowBO showBO;

    @Autowired
    private ReviewBO reviewBO;

    // GET /api/shows?genre=뮤지컬&page=0&search=
    @GetMapping("/shows")
    public Map<String, Object> showList(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(value = "genre", defaultValue = "전체") String genre,
            @RequestParam(value = "search", required = false) String search) {

        Map<String, Object> result = showBO.getShowListResult(genre, pageable, search);
        result.put("genre", genre);
        result.put("search", search);
        return result;
    }

    // GET /api/shows/{showId}
    @GetMapping("/shows/{showId}")
    public Map<String, Object> showDetail(@PathVariable int showId) {
        ShowView show = showBO.generateShowViewByShowId(showId);
        Double average = reviewBO.getReviewAveragePoint(showId);

        Map<String, Object> result = new HashMap<>();
        result.put("show", show);
        result.put("average", average != null ? average : 0.0);
        return result;
    }
}
