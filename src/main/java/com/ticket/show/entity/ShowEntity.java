package com.ticket.show.entity;

import java.sql.Date;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
@Entity
@Table(name = "\"show\"")
public class ShowEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "theaterId")
	private int theaterId;
	
	private String name;
	
	private String genre;
	

	// Date로 저장할 것인가 String으로 저장할 것인가
	@Column(name = "startDate")
	private Date startDate;
	
	@Column(name = "endDate")
	private Date endDate;
	
	@Column(name = "validStartDate")
	private Date validStartDate;
	
	@Column(name = "validEndDate")
	private Date validEndDate;
	
	
	private int time;
	
	private int age;
	
	// 추후 관리자 페이지 insert에서
	// 이거 not null로 들어가는디 어떡하지
	@Column(name = "imagePath")
	private String imagePath;
	
	@Column(name = "bannerImagePath")
	private String bannerImagePath;
	
	@Column(name = "infoImagePath")
	private String infoImagePath;
	
	@Column(name = "discountImagePath")
	private String discountImagePath;
	
	@UpdateTimestamp
	@Column(name = "createdAt", updatable = false)
	private ZonedDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name = "updatedAt")
	private ZonedDateTime updatedAt;

}
