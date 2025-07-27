package com.digi.service;

import com.digi.model.Address;
import com.digi.model.User;

public interface AddressService {

    void createUserAddress(Address address, User user);
    void updateUserAddress(int id, String country, String state, String city, String street);

}
