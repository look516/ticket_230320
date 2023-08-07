package com.ticket.show.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.show.dao.ShowMapper;
import com.ticket.show.domain.Show;

@Service
public class ShowBO {
	@Autowired
	private ShowMapper showMapper; // mybatis
	
	public Show getShowByShowId(int showId) {
		return showMapper.selectShowByShowId(showId);
	}
}
