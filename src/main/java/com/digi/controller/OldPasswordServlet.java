package com.digi.controller;

import com.digi.exceptions.UserApiException;
import com.digi.exceptions.UserBadRequestException;
import com.digi.exceptions.UserNotFoundException;
import com.digi.model.User;
import com.digi.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class OldPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserServiceImpl service = new UserServiceImpl();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String password = request.getParameter("password");

        try {
            service.checkOldPassword(user.getId(), password);
            session.setAttribute("email", user.getEmail());
            request.getRequestDispatcher("change-password-page.jsp").forward(request, response);
        }catch (UserBadRequestException | UserNotFoundException | UserApiException e){
            String errorMessage = e.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("old-password-page.jsp").forward(request, response);
        }
    }
}
