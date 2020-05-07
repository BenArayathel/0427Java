package com.application.bank.dao;

import com.application.bank.exception.BusinessException;
import com.application.bank.models.User;
import java.util.List;

public interface UserDao {

//Create
public User insertUser(User u) throws BusinessException;

//Update
public User updateUser(User u, String newAttribute, String columnName) throws BusinessException;

//Read
public User selectUserByEmail() throws BusinessException;
public List<User> selectAllUsers() throws BusinessException;

//Delete
public void removeUser() throws BusinessException;


}