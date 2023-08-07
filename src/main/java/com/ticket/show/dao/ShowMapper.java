package com.ticket.show.dao;

import org.springframework.stereotype.Repository;

import com.ticket.show.domain.Show;

@Repository
public interface ShowMapper {
	public Show selectShowByShowId(int showId);
}
