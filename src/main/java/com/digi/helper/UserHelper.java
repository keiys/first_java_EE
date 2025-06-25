package com.digi.helper;

import com.digi.exceptions.AlreadyExistException;
import com.digi.exceptions.UserBadRequestException;
import com.digi.model.User;
import com.digi.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;

public class UserHelper {

    //user name validation
    private final String NAME_REGEX = "^[A-Z][a-z]+";
    private final String NAME_NULL_MSG = "Name of the User cannot be null or empty";
    private final String NAME_REGEX_MSG = "Name of User is not valid";

    //user surname validation
    private final String SURNAME_REGEX = "^[A-Z][a-zA-Z-]+";
    private final String SURNAME_NULL_MSG = "Surname of the User cannot be null or empty";
    private final String SURNAME_REGEX_MSG = "Surname of User is not valid";

    //user email validation
    public static final String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";;
    private final String EMAIL_NULL_MSG = "Email of the User cannot be null or empty";
    private final String EMAIL_REGEX_MSG = "Email of User is not valid";

    //user password validation
    public static final String PASSWORD_REGEX =  "^(?=.*[A-Z])(?=(?:.*\\d){2,})(?=.*[!@#$%^&*()_+\\-\\[\\]{};':\"\\\\|,.<>/?]).{8,}$";
    public static final String PASSWORD_NULL_MSG = "Password of the User cannot be null or empty";
    public static final String PASSWORD_REGEX_MSG = "Password of User is not valid";

    public void validateFlields(User user){
        if(StringUtils.isBlank(user.getName())){
            throw new UserBadRequestException(NAME_NULL_MSG);
        }else if(!user.getName().matches(NAME_REGEX)){
            throw new UserBadRequestException(NAME_REGEX_MSG);
        }

        if(StringUtils.isBlank(user.getSurname())){
            throw new UserBadRequestException(SURNAME_NULL_MSG);
        }else if(!user.getSurname().matches(SURNAME_REGEX)){
            throw new UserBadRequestException(SURNAME_REGEX_MSG);
        }

        if(StringUtils.isBlank(user.getEmail())){
            throw new UserBadRequestException(EMAIL_NULL_MSG);
        }else if(!user.getEmail().matches(EMAIL_REGEX)){
            throw new UserBadRequestException(EMAIL_REGEX_MSG);
        }

        validatePassword(user.getPassword());
        validUserExistence(user.getEmail());
    }

    public void validUserExistence (String email){
        UserRepository userRepository = new UserRepository();
        User user = userRepository.getByEmail(email);
        if(user != null){
            throw new AlreadyExistException("User with this email address already exists: " + email);
        }

    }

    public void validatePassword(String password){
        if(StringUtils.isBlank(password)){
            throw new UserBadRequestException(PASSWORD_NULL_MSG);
        }else if(!password.matches(PASSWORD_REGEX)){
            throw new UserBadRequestException(PASSWORD_REGEX_MSG);
        }
    }
}
