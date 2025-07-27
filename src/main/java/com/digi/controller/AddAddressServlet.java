package com.digi.controller;

import com.digi.exceptions.AddressNotFoundException;
import com.digi.model.Address;
import com.digi.model.User;
import com.digi.service.impl.AddressServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddAddressServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AddressServiceImpl addressService = new AddressServiceImpl();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String errorMessage = null;
        String country = null;
        String city = null;
        String address = null;
        String region = null;

        if(user == null) {
            errorMessage = "User not found.";
        }else{
            country = request.getParameter("country");
            region = request.getParameter("state-region-province");
            city = request.getParameter("city");
            address = request.getParameter("address");
        }

        if(errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/add-address-page.jsp").forward(request, response);
        }else{
            Address userAddress = new Address(0, country, region, city, address, user.getId());

            try{
                addressService.createUserAddress(userAddress, user);
                request.getRequestDispatcher("/home-page.jsp").forward(request, response);
            }catch (AddressNotFoundException e){
                errorMessage = e.getMessage();
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("/add-address-page.jsp").forward(request, response);
            }
        }

    }


}
