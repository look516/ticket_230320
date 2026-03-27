package com.ticket.theater.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticket.theater.entity.TheaterEntity;

public interface TheaterRepository extends JpaRepository<TheaterEntity, Integer> {
	// findById(Integer id) → Optional<TheaterEntity> 는 JpaRepository가 제공
	Optional<TheaterEntity> findByName(String name);
}
