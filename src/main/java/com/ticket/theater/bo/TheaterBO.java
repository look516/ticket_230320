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
		return theaterRepository.findById(theaterId);
	}
}
