package com.digi.controller;

import com.digi.exceptions.AddressApiException;
import com.digi.exceptions.UserApiException;
import com.digi.model.User;
import com.digi.service.impl.AddressServiceImpl;
import com.digi.service.impl.UserServiceImpl;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditProfileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AddressServiceImpl addressService = new AddressServiceImpl();
        UserServiceImpl userService = new UserServiceImpl();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        Integer year = 0;
        if(!StringUtils.isEmpty(request.getParameter("year"))){
            year = Integer.parseInt(request.getParameter("year"));
        }
        String country = request.getParameter("country");
        String state = request.getParameter("state-region-province");
        String city = request.getParameter("city");
        String street = request.getParameter("address");

        try{
            userService.updateUser(user.getId(), name, surname, year);
            addressService.updateUserAddress(user.getId(), country, state, city, street);
            request.getRequestDispatcher("home-page.jsp").forward(request, response);
        }catch (UserApiException | AddressApiException e){
            String errorMessage = e.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("edit-profile-page.jsp").forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("edit-profile-page.jsp").forward(request, response);
    }
}
