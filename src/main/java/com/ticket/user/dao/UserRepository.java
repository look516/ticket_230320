package com.ticket.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticket.user.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	UserEntity findByLoginId(String loginId);
	UserEntity findByLoginIdAndPassword(String loginId, String password);
}
