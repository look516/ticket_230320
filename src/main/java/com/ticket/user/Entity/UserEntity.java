package com.ticket.user.Entity;

import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@DynamicInsert
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "user")
@Entity
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "loginId")
	private String loginId;
	
	private String password;
	
	private String name;
	
	private String email;
	
	@Column(name = "phoneNumber")
	private String phoneNumber;
	
	private String grade;
	
	@UpdateTimestamp
	@Column(name = "createdAt")
	private ZonedDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name = "updatedAt")
	private ZonedDateTime updatedAt;
}
