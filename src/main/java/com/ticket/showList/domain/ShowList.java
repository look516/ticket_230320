package com.ticket.showList.domain;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
@Entity
public class ShowList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String mt20id; // 공연아이디
	
	private String prfnm; // 공연명
	
	private String prfpdfrom; // 시작일
	
	private String prfpdto; // 종료일
	
	private String fcltynm; // 공연장 - 공연장 DB에도 쌓든, 별도로 하든
	
	private String poster; // 이미지
	
	private String genrenm; // 장르
	
	private String prfstate; // 공연상태
	
	private String openrun; // 오픈런여부
	
	@UpdateTimestamp
	@Column(name = "createdAt", updatable = false)
	private ZonedDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name = "updatedAt")
	private ZonedDateTime updatedAt;
}
