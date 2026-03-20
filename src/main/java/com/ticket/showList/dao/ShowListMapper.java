package com.ticket.showList.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ticket.show.domain.Show;

@Mapper
public interface ShowListMapper {
	public void insertShowList(
			@Param("list") List<Show> showList);
	
}
