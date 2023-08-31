package com.ticket.show.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticket.show.entity.ShowEntity;

@Repository
public interface ShowRepository extends JpaRepository<ShowEntity, Integer> {
	public Page<ShowEntity> findByGenreOrderByIdDesc(String genre, Pageable pageable);
	public Page<ShowEntity> findAllByOrderByIdDesc(Pageable pageable);
	public Page<ShowEntity> findByNameContaining(String search, Pageable pageable);
	
	
	// optional 때문에 임시적으로 만들었다.
	public ShowEntity findById(int showId);
}
