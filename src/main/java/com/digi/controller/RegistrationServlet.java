package com.digi.controller;

import com.digi.exceptions.AlreadyExistException;
import com.digi.exceptions.UserApiException;
import com.digi.exceptions.UserBadRequestException;
import com.digi.model.User;
import com.digi.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService service = new UserService();
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

            try{
                service.createUser(user);
            }catch (UserBadRequestException | UserApiException | AlreadyExistException e){
                errorMessage = e.getMessage();
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("/registration-page.jsp").forward(request, response);
            }
        }

        response.sendRedirect("/login-page.html");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
