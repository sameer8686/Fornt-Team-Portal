package com.sam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sam.entities.UserDtlsEntity;

public interface UserDtlsRepo extends JpaRepository<UserDtlsEntity, Integer> {

	public UserDtlsEntity findByEmail(String email);
	
	public UserDtlsEntity findByEmailAndPwd(String email,String pwd);
	
	
}
