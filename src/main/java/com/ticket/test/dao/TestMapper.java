package com.ticket.test.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface TestMapper {
	public List<Map<String, Object>> selectTestList();
}
