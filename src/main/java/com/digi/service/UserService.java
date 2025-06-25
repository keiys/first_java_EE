package com.digi.service;

import com.digi.enums.Status;
import com.digi.helper.UserHelper;
import com.digi.model.User;
import com.digi.repository.UserRepository;
import com.digi.util.GenerateToken;

public class UserService {
    public void createUser(User user) {
        UserHelper userHelper = new UserHelper();
        UserRepository userRepository = new UserRepository();
        String verifyCode = GenerateToken.generateVerifyCode();
        user.setVerificationCode(verifyCode);
        user.setStatus(String.valueOf(Status.INACTIVE));
        userHelper.validateFlields(user);
        userRepository.create(user);
    }
}
