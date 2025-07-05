package com.digi.controller;

import com.digi.exceptions.UserApiException;
import com.digi.exceptions.UserNotFoundException;
import com.digi.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ResetPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserServiceImpl service = new UserServiceImpl();
        String email = request.getParameter("email");

        try {
            service.resetPassword(email);
            session.setAttribute("email", email);
            request.getRequestDispatcher("/input-token-page.jsp").forward(request, response);
        } catch (UserNotFoundException | UserApiException e) {
            String errorMessage = e.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/reset-password-page.jsp").forward(request, response);
        }
    }
}
