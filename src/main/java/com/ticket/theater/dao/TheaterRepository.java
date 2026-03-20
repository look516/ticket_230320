package com.ticket.theater.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticket.theater.entity.TheaterEntity;

public interface TheaterRepository extends JpaRepository<TheaterEntity, Integer> {
	// findById(Integer id) → Optional<TheaterEntity> 는 JpaRepository가 제공
}
