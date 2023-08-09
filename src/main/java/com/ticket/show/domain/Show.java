package com.ticket.show.domain;

import java.time.ZonedDateTime;

import lombok.Data;
import lombok.ToString;


// 미사용


@ToString
@Data
public class Show {
	private int id;
	private int theaterId;
	private String name;
	private String genre;
	
	// Date로 저장할 것인가 String으로 저장할 것인가
	private String startDate;
	private String endDate;
	private String validStartDate;
	private String validEndDate;
	
	private int time;
	private int age;
	
	// 추후 관리자 페이지 insert에서
	// 이거 not null로 들어가는디 어떡하지
	private String imagePath;
	private String bannerImagePath;
	private String infoImagePath;
	private String discountImagePath;
	
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
}
