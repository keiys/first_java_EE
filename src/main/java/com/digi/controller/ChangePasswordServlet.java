package com.digi.controller;

import com.digi.exceptions.UserApiException;
import com.digi.exceptions.UserBadRequestException;
import com.digi.exceptions.UserNotFoundException;
import com.digi.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

public class ChangePasswordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserServiceImpl userService = new UserServiceImpl();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        System.out.println(password);
        System.out.println(confirmPassword);

        try{
            userService.changePassword(email, password, confirmPassword);
            session.removeAttribute("email");
            response.sendRedirect("login-page.jsp");
        }catch (UserBadRequestException | UserApiException | UserNotFoundException e){
            String errorMessage = e.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("change-password-page.jsp").forward(request, response);
        }

    }
}
