package com.digi.controller;

import com.digi.exceptions.UserApiException;
import com.digi.exceptions.UserBadRequestException;
import com.digi.exceptions.UserNotFoundException;
import com.digi.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class InputTokenServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserServiceImpl userService = new UserServiceImpl();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        String resetToken = request.getParameter("resetToken");

        try {
            userService.checkResetToken(email, resetToken);
        } catch (UserApiException | UserBadRequestException | UserNotFoundException e) {
            String errorMessage = e.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/input-token-page.jsp").forward(request, response);
        }
        response.sendRedirect("/change-password-page.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserServiceImpl userService = new UserServiceImpl();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        try {
            userService.sendResetToken(email);
            response.sendRedirect("/input-token-page.jsp");
        } catch (UserBadRequestException | UserNotFoundException e) {
            String errorMessage = e.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/input-token-page.jsp").forward(request, response);
        }
    }
}
