package com.digi.service;

import com.digi.model.User;
import com.digi.service.impl.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;

public interface UserService {

    void createUser(User user);

    boolean verifyEmail(String verifyCode, String email);


    boolean sendVerifyCode(String email);
    boolean sendResetToken(String email);
    void resetPassword(String email);
    boolean checkResetToken(String email, String resetToken);
    void changePassword(String email, String password, String confirmPassword);
    User login(String email, String password);
    boolean checkOldPassword(int id, String oldPassword);
    void updateUser(int id, String name, String surename, int year);
}
