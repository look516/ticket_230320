package com.ticket.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.user.Entity.UserEntity;
import com.ticket.user.dao.UserRepository;

@Service
public class UserBO {
	
	@Autowired
	private UserRepository userRepository;
	
	// loginId로 리턴타입 설정않는 이유는?
	public UserEntity getUserEntityByLoginId(String loginId) {
		return userRepository.findByLoginId(loginId);
	}
	
	public UserEntity getUserEntityById(int id) {
		return userRepository.findById(id).orElse(null);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Integer addUser(String loginId, String password, String name, String email, String phoneNumber) {
		// save
		UserEntity userEntity = userRepository
				.save(UserEntity.builder()
						.loginId(loginId)
						.password(password)
						.name(name)
						.email(email)
						.phoneNumber(phoneNumber)
						.build());
		return userEntity == null ? null : userEntity.getId();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public UserEntity getUserEntityByLoginIdPassword(String loginId, String password) {
		return userRepository.findByLoginIdAndPassword(loginId, password);
	}
}
