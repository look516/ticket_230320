package com.ticket.showList.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ticket.show.domain.Show;

@Repository
public interface ShowListMapper {
	public void insertShowList(
			@Param("list") List<Show> showList);
	
}
