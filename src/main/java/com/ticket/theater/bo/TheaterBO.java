package com.ticket.theater.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.theater.dao.TheaterRepository;
import com.ticket.theater.entity.TheaterEntity;

@Service
public class TheaterBO {

	@Autowired
	private TheaterRepository theaterRepository;

	public TheaterEntity getTheaterEntityById(int theaterId) {
		return theaterRepository.findById(theaterId).orElse(null);
	}

	/**
	 * 공연장 이름으로 조회하되, 없으면 새로 insert하고 반환 (upsert)
	 */
	public TheaterEntity getOrCreateTheaterByName(String name) {
		return theaterRepository.findByName(name)
				.orElseGet(() -> theaterRepository.save(
						TheaterEntity.builder()
								.name(name)
								.build()
				));
	}
}
