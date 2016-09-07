package com.github.dao;

import com.github.Role;
import com.github.Status;
import com.github.User;

import java.util.List;

public interface UserDao {

    void login(User user);
    void logout(User user);
    void kick(User user);
    Status getStatus(User user);
    Role getRole(User user);
    void unkick(User user);
    List<User> getLoggedinUsers();
}
