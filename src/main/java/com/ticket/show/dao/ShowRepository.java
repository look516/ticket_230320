package com.ticket.show.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticket.show.domain.Show;
import com.ticket.show.entity.ShowEntity;

@Repository
public interface ShowRepository extends JpaRepository<ShowEntity, Integer> {
	public List<ShowEntity> findByGenreOrderByIdDesc(String genre);
	public List<ShowEntity> findAllByOrderByIdDesc();
	
	
	// optional 때문에 임시적으로 만들었다.
	public ShowEntity findById(int showId);
}
