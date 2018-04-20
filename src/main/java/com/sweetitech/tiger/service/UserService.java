package com.sweetitech.tiger.service;

import java.util.Arrays;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.sweetitech.tiger.dto.UserDto;
import org.sweetitech.tiger.dto.UserProfileDto;

import com.sweetitech.tiger.config.Constants;
import com.sweetitech.tiger.exception.UserAlreadyExistException;
import com.sweetitech.tiger.model.News;
import com.sweetitech.tiger.model.PasswordResetToken;
import com.sweetitech.tiger.model.Role;
import com.sweetitech.tiger.model.User;
import com.sweetitech.tiger.repository.PasswordResetTokenRepository;
import com.sweetitech.tiger.repository.RoleRepository;
import com.sweetitech.tiger.repository.UsersRepository;
import com.sweetitech.tiger.service.interfaces.IUserService;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UsersRepository repository;


    @Autowired
    private PasswordResetTokenRepository passwordTokenRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public static final String TOKEN_INVALID = "invalidToken";
    public static final String TOKEN_EXPIRED = "expired";
    public static final String TOKEN_VALID = "valid";

  //  public static String QR_PREFIX = "https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=";
  //  public static String APP_NAME = "SpringRegistration";

    // API

    @Override
    public User registerNewUserAccount(final UserDto accountDto) {
        if (emailExist(accountDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with this email adress: " + accountDto.getEmail());
        }
        
        if(userNameExist(accountDto.getUserName())) {
        		throw new UserAlreadyExistException("There is an account with this userName: " + accountDto.getUserName());
        }
        final User user = new User();

        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        user.setUserName(accountDto.getUserName());
        user.setPhoneNumber(accountDto.getPhoneNumber());
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        
        return repository.save(user);
    }

   


    @Override
    public void saveRegisteredUser(final User user) {
        repository.save(user);
    }

    @Override
    public void deleteUser(final User user) {

        final PasswordResetToken passwordToken = passwordTokenRepository.findByUser(user);

        if (passwordToken != null) {
            passwordTokenRepository.delete(passwordToken);
        }

        repository.delete(user);
    }


    

    @Override
    public void createPasswordResetTokenForUser(final User user, final String token) {
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
    }

    @Override
    public User findUserByEmail(final String email) {
        return repository.findByEmail(email);
    }

    @Override
    public PasswordResetToken getPasswordResetToken(final String token) {
        return passwordTokenRepository.findByToken(token);
    }

    @Override
    public User getUserByPasswordResetToken(final String token) {
        return passwordTokenRepository.findByToken(token).getUser();
    }

    @Override
    public User getUserByID(final long id) {
        return repository.findById(id);
    }

    @Override
    public void changeUserPassword(final User user, final String password) {
        user.setPassword(password);//(passwordEncoder.encode(password));
        repository.save(user);
    }

    @Override
    public boolean checkIfValidOldPassword(final User user, final String oldPassword) {
        return false;//passwordEncoder.matches(oldPassword, user.getPassword());
    }

   

    private boolean emailExist(final String email) {
        return repository.findByEmail(email) != null;
    }


    private boolean userNameExist(final String userName) {
        return repository.findByUserName(userName) != null;
    }
    

	@Override
	public UserProfileDto findUserProfileDtoByEmail(String email) {
		
		User u = repository.findByEmail(email);
		UserProfileDto udto = new UserProfileDto();
        
		if(u==null) {
			return null;
		}else {
        udto.setEmail(u.getEmail());
        udto.setPhoneNumber(u.getPhoneNumber());
        udto.setFirstName(u.getFirstName());
        udto.setLastName(u.getLastName());
        udto.setProfilePicture(u.getProfilePicture());
        
        
        String role = "";
        Iterator<Role> roles = u.getRoles().iterator();
		while (roles.hasNext()) {
			Role r = roles.next();
			
			role = r.getName();
//			Iterator<Privilege> privileges = r.getPrivileges().iterator();
//			while (privileges.hasNext()) {
//				Privilege privilege1 = privileges.next();
//				if(privilege1.getName().equals(privilege)) {
//					flag = true;
//					break;
//				}
//			}
		}
		
		
        udto.setRole(role);
        
		return udto;
		}
	}
	
	
	@Override
	public Page<User> findAllUser(int page) {
		
		PageRequest request =
	            new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		return repository.findAll(request);
	         
	}
	

}