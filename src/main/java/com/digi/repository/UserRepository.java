package com.digi.repository;
import com.digi.exceptions.UserApiException;
import com.digi.model.User;
import com.digi.util.MyDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRepository {

    /**
     * Repository method for creating user in DB
     * @param user to be created
     */
    public void create(User user){
        Connection connection = MyDataSource.getConnection();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setInt(4, user.getYear());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setString(7, user.getVerificationCode());
            preparedStatement.setString(8, user.getStatus());
            preparedStatement.setString(9, user.getResetToken());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new UserApiException("Error while creating user");
        }
    }

    public User getByEmail(String email)  {
        Connection connection = MyDataSource.getConnection();
        ResultSet resultSet;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
        }catch (SQLException e) {
            throw new UserApiException("Error while getting user by email");
        }
        return toUser(resultSet);

    }

    private User toUser (ResultSet resultSet){
        User user = null;
        try{
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("first_name");
                String surname = resultSet.getString("last_name");
                int year = resultSet.getInt("year");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                user = new User(id, name, surname, year, email, password, null, null, null);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
