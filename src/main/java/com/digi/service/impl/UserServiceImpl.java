package com.digi.service.impl;

import com.digi.config.EmailSender;
import com.digi.enums.Status;
import com.digi.exceptions.UserBadRequestException;
import com.digi.exceptions.UserNotFoundException;
import com.digi.helper.UserHelper;
import com.digi.model.User;
import com.digi.repository.UserRepository;
import com.digi.service.UserService;
import com.digi.util.GenerateToken;
import org.apache.commons.lang3.StringUtils;

public class UserServiceImpl implements UserService {
    public void createUser(User user) {
        UserHelper userHelper = new UserHelper();
        UserRepository userRepository = new UserRepository();
        String verifyCode = GenerateToken.generateVerifyCode();
        user.setVerificationCode(verifyCode);
        user.setStatus(Status.INACTIVE);
        userHelper.validateFlields(user);
        userRepository.create(user);
        EmailSender.sendEmail(user.getEmail(), "Verify Code", "Your verification code is: " + verifyCode);

    }

    @Override
    public boolean verifyEmail(String verifyCode, String email) {
        UserRepository userRepository = new UserRepository();
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
        UserRepository userRepository = new UserRepository();
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
        UserRepository repository = new UserRepository();
        User user = repository.getByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found with given email");
        }
        String resetToken = GenerateToken.generateResetToken();
        repository.updateResetToken(user.getId(), resetToken);
        EmailSender.sendEmail(user.getEmail(), "Reset Password", "Token for updating password is: " + resetToken);
    }


}
