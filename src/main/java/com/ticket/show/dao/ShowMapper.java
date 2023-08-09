package com.ticket.show.dao;

import org.springframework.stereotype.Repository;

import com.ticket.show.domain.Show;

// 미사용

@Repository
public interface ShowMapper {
	public Show selectShowByShowId(int showId);
}
