package com.sweetitech.tiger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.sweetitech.tiger.repository.UsersRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    UsersRepository usersRepository;

//    @Override
//    public CustomUserDetailsService(UserService userService) {
//        this.userService = userService;
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	
    	
//    	System.out.println("============================ given string: " + email);
//    	
    	
    	UserDetails ud = this.usersRepository.findByEmail(email);
    	if(ud == null) {
//    		
//    		String phoneNumber = email;
//    		System.out.println("============================ enter user details null with email ");
//    		
    		ud = this.usersRepository.findByPhoneNumber(email);
    	}
        return ud;
    }
}