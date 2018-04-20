package com.sweetitech.tiger.service.interfaces;

import org.springframework.data.domain.Page;
import org.sweetitech.tiger.dto.UserDto;
import org.sweetitech.tiger.dto.UserProfileDto;

import com.sweetitech.tiger.exception.UserAlreadyExistException;
import com.sweetitech.tiger.model.PasswordResetToken;
import com.sweetitech.tiger.model.User;

public interface IUserService {

    User registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException;

    //User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    void deleteUser(User user);

    void createPasswordResetTokenForUser(User user, String token);

    User findUserByEmail(String email);

    PasswordResetToken getPasswordResetToken(String token);

    User getUserByPasswordResetToken(String token);

    User getUserByID(long id);

    void changeUserPassword(User user, String password);

    boolean checkIfValidOldPassword(User user, String password);

   // String validateVerificationToken(String token);
    
    public UserProfileDto findUserProfileDtoByEmail(final String email);
    public Page<User> findAllUser(int page) ;
  
}
