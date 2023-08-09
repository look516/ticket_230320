package com.ticket.show.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticket.show.entity.ShowEntity;

@Repository
public interface ShowRepository extends JpaRepository<ShowEntity, Integer> {
	public List<ShowEntity> findByGenreOrderByIdDesc(String genre);
	public List<ShowEntity> findAllByOrderByIdDesc();
}
