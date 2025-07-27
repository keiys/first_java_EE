package com.digi.repository;

import com.digi.exceptions.AddressApiException;
import com.digi.exceptions.UserApiException;
import com.digi.model.Address;
import com.digi.util.MyDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface AddressRepository {
    public void create(Address address, int user_id);

    public Address getAddressById(int id);

    public void updateAddress(int user_id, Address address);
}
