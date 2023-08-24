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
public class ShowDB {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String mt20id; // 공연아이디
	
	private String mt10id; // 공연시설ID
	
	private String prfnm; // 공연명
	
	private String prfpdfrom; // 시작일
	
	private String prfpdto; // 종료일
	
	private String fcltynm; // 공연장 - 공연장 DB에도 쌓든, 별도로 하든
	
	private String prfcast; // 공연출연진
	
	private String prfcrew; // 공연제작진
	
	private String prfruntime; // 런타임
	
	private String prfage; // 연령
	
	private String entrpsnm; // 제작사
	
	private String pcseguidance; // 티켓가격
	
	private String sty; // 줄거리
	
	private String poster; // 이미지
	
	private String genrenm; // 장르
	
	private String prfstate; // 공연상태
	
	private String openrun; // 오픈런여부
	
	private String styurl1; // 소개이미지
	
	private String styurl2; // 소개이미지
	
	private String styurl3; // 소개이미지
	
	private String styurl4; // 소개이미지
	
	private String dtguidance; // 공연시간
	
	@UpdateTimestamp
	@Column(name = "createdAt", updatable = false)
	private ZonedDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name = "updatedAt")
	private ZonedDateTime updatedAt;
}
