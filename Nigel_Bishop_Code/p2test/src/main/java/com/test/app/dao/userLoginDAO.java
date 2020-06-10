package com.test.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.app.model.UserLogin;

@Repository
public interface userLoginDAO extends JpaRepository<UserLogin, Integer>{

}
