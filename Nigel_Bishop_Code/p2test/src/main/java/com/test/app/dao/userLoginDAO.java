package com.test.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.app.model.UserLogin;

@Repository
public interface userLoginDAO extends JpaRepository<UserLogin, Integer>{
	
//	public UserLogin findByUname(String userName);
	public UserLogin findByuserName(String userName);



}
