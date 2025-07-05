package com.digi.controller;

import com.digi.enums.Status;
import com.digi.exceptions.UserApiException;
import com.digi.exceptions.UserBadRequestException;
import com.digi.exceptions.UserNotFoundException;
import com.digi.model.User;
import com.digi.repository.UserRepository;
import com.digi.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class VerificationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserServiceImpl userService = new UserServiceImpl();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String verifyCode = request.getParameter("verifyCode");

        try {
            userService.verifyEmail(verifyCode, email);
        } catch (UserApiException | UserBadRequestException | UserNotFoundException e) {
            String errorMessage = e.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/verification-page.jsp").forward(request, response);
        }
        response.sendRedirect("/login-page.html");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserServiceImpl userService = new UserServiceImpl();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        try {
            userService.sendVerifyCode(email);
            response.sendRedirect("/verification-page.jsp");
        } catch (UserBadRequestException | UserNotFoundException e) {
            String errorMessage = e.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/verification-page.jsp").forward(request, response);
        }
    }
}
