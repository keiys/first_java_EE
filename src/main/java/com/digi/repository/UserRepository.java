package com.digi.repository;
import com.digi.enums.Status;
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
            preparedStatement.setString(8, String.valueOf(user.getStatus()));
            preparedStatement.setString(9, user.getResetToken());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new UserApiException("Error while creating user", e);
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

    public void updateStatus(int id, Status status, String verifyCode)  {
        Connection connection = MyDataSource.getConnection();

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update users set status =?, verification_code = ? where id = ?");

            preparedStatement.setString(1, String.valueOf(status));
            preparedStatement.setString(2, null);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            throw new UserApiException("Error while updating status of user");
        }
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
                String verificationCode = resultSet.getString("verification_code");
                String status = resultSet.getString("status");
                String resetToken = resultSet.getString("reset_token");
                user = new User(id, name, surname, year, email, password, verificationCode, Enum.valueOf(Status.class, status), resetToken);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public void updateResetToken(int id, String resetToken) {
        Connection connection = MyDataSource.getConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update users set reset_token = ? where id = ?");

            preparedStatement.setString(1, resetToken);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new UserApiException("Error while updating reset token");
        }
    }

}
