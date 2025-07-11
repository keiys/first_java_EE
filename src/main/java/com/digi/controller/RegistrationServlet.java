package com.digi.controller;


import com.digi.exceptions.UserAlreadyExistException;
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

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserServiceImpl service = new UserServiceImpl();
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String year = request.getParameter("year");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int parsedYear = 0;
        String errorMessage = null;

        try{
            parsedYear = Integer.parseInt(year);
        }catch (NumberFormatException e){
            errorMessage = "Invalid year";
        }

        if(errorMessage != null){
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/registration-page.jsp").forward(request, response);
        }else {
            User user = new User(0, name, surname,parsedYear , email, password, null, null, null);

            try {
                service.createUser(user);
                session.setAttribute("email", email);
            } catch (UserBadRequestException | UserApiException| UserAlreadyExistException e){
                errorMessage = e.getMessage();
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("/registration-page.jsp").forward(request, response);
            }
        }

        request.getRequestDispatcher("/verification-page.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
