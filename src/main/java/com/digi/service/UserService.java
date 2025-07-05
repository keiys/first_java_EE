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

    void resetPassword(String email);
}
