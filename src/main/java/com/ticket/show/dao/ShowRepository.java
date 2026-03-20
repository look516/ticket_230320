package com.ticket.show.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ticket.show.entity.ShowEntity;

public interface ShowRepository extends JpaRepository<ShowEntity, Integer> {
	Page<ShowEntity> findByGenreOrderByIdDesc(String genre, Pageable pageable);
	Page<ShowEntity> findAllByOrderByIdDesc(Pageable pageable);
	Page<ShowEntity> findByNameContaining(String search, Pageable pageable);
	// findById(Integer id)는 JpaRepository가 Optional<ShowEntity>로 이미 제공 → 별도 선언 불필요
}
