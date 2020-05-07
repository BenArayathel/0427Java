package com.application.bank.dao;

import com.application.bank.models.User;
import java.util.List;

public interface UserDao {

//Create
public void insertUser(User u);

//Update
public void updateUser();

//Read
public User selectUserByEmail();
public List<User> selectAllUsers();

//Delete
public void removeUser();


}