package com.digi.controller;

import com.digi.exceptions.UserApiException;
import com.digi.exceptions.UserBadRequestException;
import com.digi.model.User;
import com.digi.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserServiceImpl userService = new UserServiceImpl();
        HttpSession session = request.getSession();
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();

        try{
            User user = userService.login(email, password);
            session.setAttribute("user", user);
            request.getRequestDispatcher("home-page.jsp").forward(request, response);
        }catch (UserBadRequestException | UserApiException e){
            String errorMessage = e.getMessage();
            session.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("login-page.jsp").forward(request, response);
        }

    }
}
