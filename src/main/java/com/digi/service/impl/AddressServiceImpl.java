package com.digi.service.impl;

import com.digi.exceptions.AddressBadRequestException;
import com.digi.model.Address;
import com.digi.model.User;
import com.digi.repository.impl.AddressRepositoryImpl;
import com.digi.service.AddressService;
import org.apache.commons.lang3.StringUtils;

public class AddressServiceImpl implements AddressService {

    public void createUserAddress(Address address, User user) {
        AddressRepositoryImpl addressRepository = new AddressRepositoryImpl();
        if(StringUtils.isEmpty(address.getCountry())){
            throw new AddressBadRequestException("Country can not be empty");
        }
        addressRepository.create(address, user.getId());
    }

    @Override
    public void updateUserAddress(int id, String country, String state, String city, String street) {
        AddressRepositoryImpl addressRepository = new AddressRepositoryImpl();
        Address address = addressRepository.getAddressById(id);
        if(!StringUtils.isEmpty(country)){
            address.setCountry(country);
        }
        if(!StringUtils.isEmpty(state)){
            address.setRegion(state);
        }
        if(!StringUtils.isEmpty(city)){
            address.setCity(city);
        }
        if(!StringUtils.isEmpty(street)){
            address.setStreet(street);
        }
        if(StringUtils.isEmpty(country) && StringUtils.isEmpty(state) && StringUtils.isEmpty(city) && StringUtils.isEmpty(street)){
            return;
        }
        addressRepository.updateAddress(id, address);

    }


}
