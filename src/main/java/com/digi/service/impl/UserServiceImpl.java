package com.digi.service.impl;

import com.digi.config.EmailSender;
import com.digi.enums.Status;
import com.digi.exceptions.AddressBadRequestException;
import com.digi.exceptions.UserBadRequestException;
import com.digi.exceptions.UserNotFoundException;
import com.digi.helper.UserHelper;
import com.digi.model.User;
import com.digi.repository.impl.UserRepositoryImpl;
import com.digi.repository.jpa.UserRepositoryJpa;
import com.digi.service.UserService;
import com.digi.util.GenerateToken;
import org.apache.commons.lang3.StringUtils;

public class UserServiceImpl implements UserService {
    public void createUser(User user) {
        UserHelper userHelper = new UserHelper();
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        UserRepositoryJpa userRepositoryJpa = new UserRepositoryJpa();
        String verifyCode = GenerateToken.generateVerifyCode();
        user.setVerificationCode(verifyCode);
        user.setStatus(Status.INACTIVE);
        userHelper.validateFlields(user);
        // userRepository.create(user);
        userRepositoryJpa.create(user);
        EmailSender.sendEmail(user.getEmail(), "Verify Code", "Your verification code is: " + verifyCode);

    }

    @Override
    public boolean verifyEmail(String verifyCode, String email) {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        User user = userRepository.getByEmail(email);
        if(user == null){
            throw new UserNotFoundException("User not found in current session");
        }
        if(StringUtils.isEmpty(user.getVerificationCode()) && Status.ACTIVE.equals(user.getStatus())){
            throw new UserBadRequestException("User is already verified");
        }
        if(!StringUtils.isEmpty(verifyCode) && !verifyCode.equals(user.getVerificationCode())){
            throw new UserBadRequestException("Invalid verification code");
        }
        if(verifyCode.equals(user.getVerificationCode())){
            userRepository.updateStatus(user.getId(), Status.ACTIVE, verifyCode);
        }

        return true;
    }

    @Override
    public boolean sendVerifyCode(String email) {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        User user = userRepository.getByEmail(email);

        if(user == null){
            throw new UserNotFoundException("User not found in current session");
        }
        if(user.getStatus().equals(Status.INACTIVE)){
            EmailSender.sendEmail(user.getEmail(), "Verify Code", "Your verification code is: " + user.getVerificationCode());
        }else{
            throw new UserBadRequestException("Your email already verified");
        }
        return true;
    }

    @Override
    public void resetPassword(String email) {
        UserRepositoryImpl repository = new UserRepositoryImpl();
        User user = repository.getByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found with given email");
        }
        String resetToken = GenerateToken.generateResetToken();
        repository.updateResetToken(user.getId(), resetToken);
        EmailSender.sendEmail(user.getEmail(), "Reset Password", "Token for updating password is: " + resetToken);
    }


    @Override
    public boolean sendResetToken(String email) {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        User user = userRepository.getByEmail(email);

        if(user == null){
            throw new UserNotFoundException("User not found in current session");
        }
        if(!StringUtils.isBlank(user.getResetToken())){
            EmailSender.sendEmail(user.getEmail(), "Reset Code", "Your code for reset password is: " + user.getResetToken());
        }else{
            throw new UserBadRequestException("You already reset your password");
        }
        return true;
    }

    @Override
    public boolean checkResetToken(String email, String resetToken) {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        User user = userRepository.getByEmail(email);
        if(user == null){
            throw new UserNotFoundException("User not found in current session");
        }
        if(!StringUtils.isEmpty(resetToken)){
            if(!resetToken.equals(user.getResetToken())){
                throw new UserBadRequestException("Invalid verification code");
            }
        }else{
            throw new UserBadRequestException("Invalid verification code");
        }

        userRepository.updateResetToken(user.getId(), null);

        return true;
    }

    @Override
    public void changePassword(String email, String password, String confirmPassword) {
        if(!StringUtils.isEmpty(password) && !password.equals(confirmPassword)){
            throw new UserBadRequestException("Passwords do not match");
        }
        //UserRepositoryImpl userRepository = new UserRepositoryImpl();
        UserRepositoryJpa jpa = new UserRepositoryJpa();
        User user = jpa.getByEmail(email);
        if(user == null){
            throw new UserNotFoundException("User not found in current session");
        }
        jpa.updatePassword(user.getId(), password);
    }

    @Override
    public User login(String email, String password) {
        UserRepositoryJpa jpa = new UserRepositoryJpa();
        //UserRepositoryImpl repository = new UserRepositoryImpl();
        User user = jpa.getByEmail(email);

        if(user == null){
            throw new UserBadRequestException("Username or password is not correct");
        }
        if(!user.getPassword().equals(password)){
            throw new UserBadRequestException("Username or password is not correct");
        }
        return user;
    }

    @Override
    public boolean checkOldPassword(int id, String oldPassword) {
        UserRepositoryJpa jpa = new UserRepositoryJpa();
        //UserRepositoryImpl repository = new UserRepositoryImpl();
        User user = jpa.getById(id);

        if(user == null){
            throw new UserNotFoundException("User not found");
        }
        if(!StringUtils.isEmpty(oldPassword)  && !oldPassword.equals(user.getPassword())){
            throw new AddressBadRequestException("Wrong old password");
        }
        if(StringUtils.isEmpty(oldPassword)){
            throw new AddressBadRequestException("Please write your old password");
        }
        return true;
    }

    @Override
    public void updateUser(int id, String name, String surename, int year) {
        UserRepositoryJpa jpa = new UserRepositoryJpa();
        //UserRepositoryImpl repository = new UserRepositoryImpl();
        User user = jpa.getById(id);
        if(!StringUtils.isEmpty(name)){
            user.setName(name);
        }
        if(!StringUtils.isEmpty(surename)){
            user.setSurname(surename);
        }
        if(year > 1920 && year < 2025){
            user.setYear(year);
        }
        if(StringUtils.isEmpty(name) && StringUtils.isEmpty(surename) && year == 0){
            return;
        }
        jpa.updateUser(user, user.getId());

    }


}
