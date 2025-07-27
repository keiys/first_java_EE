package com.digi.repository;

import com.digi.enums.Status;
import com.digi.model.User;

import java.sql.ResultSet;

public interface UserRepository {

    public void create(User user);
    public User getByEmail(String email);
    public User getById(int id);
    public void updateStatus(int id, Status status, String verifyCode);
    public void updateResetToken(int id, String resetToken);
    public void updatePassword(int id, String password);
    public void updateUser(User user, int id);
}
