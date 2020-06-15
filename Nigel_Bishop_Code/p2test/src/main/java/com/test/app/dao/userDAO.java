package com.test.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.app.model.user;

@Repository
public interface userDAO extends JpaRepository<user, Integer>{

}
