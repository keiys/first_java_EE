package com.digi.repository.impl;

import com.digi.exceptions.AddressApiException;
import com.digi.exceptions.UserApiException;
import com.digi.model.Address;
import com.digi.repository.AddressRepository;
import com.digi.util.MyDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressRepositoryImpl implements AddressRepository {

    public void create(Address address, int user_id){
        Connection connection = MyDataSource.getConnection();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("insert into address values (?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, address.getId());
            preparedStatement.setString(2, address.getCountry().trim());
            preparedStatement.setString(3, address.getRegion().trim());
            preparedStatement.setString(4, address.getCity().trim());
            preparedStatement.setString(5, address.getStreet().trim());
            preparedStatement.setInt(6, user_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new AddressApiException("Error while creating address", e);
        }
    }

    public Address getAddressById(int id)  {
        Connection connection = MyDataSource.getConnection();
        ResultSet resultSet;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select * from address where user_id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
        }catch (SQLException e) {
            throw new UserApiException("Error while getting address by user id");
        }
        return toAddress(resultSet);

    }

    private Address toAddress (ResultSet resultSet){
        Address address = null;
        try{
            while(resultSet.next()){
                int addressId = resultSet.getInt("address_id");
                String country = resultSet.getString("country");
                String region = resultSet.getString("state_region_province");
                String city = resultSet.getString("city");
                String addressOfUser = resultSet.getString("address");
                int userId = resultSet.getInt("user_id");
                address = new Address(addressId, country, region, city, addressOfUser, userId);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return address;
    }

    public void updateAddress(int user_id, Address address){
        Connection connection = MyDataSource.getConnection();

        try{
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update address set country =?, state_region_province = ?, city =?, address = ? where user_id = ?");
            preparedStatement.setString(1, address.getCountry());
            preparedStatement.setString(2, address.getRegion());
            preparedStatement.setString(3, address.getCity());
            preparedStatement.setString(4, address.getStreet());
            preparedStatement.setInt(5, user_id);
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            throw new UserApiException("Error while updating address");
        }
    }
}
