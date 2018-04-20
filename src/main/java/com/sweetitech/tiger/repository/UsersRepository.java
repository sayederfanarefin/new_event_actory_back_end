package com.sweetitech.tiger.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sweetitech.tiger.model.User;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	User findById(long id);
	User findByPhoneNumber(String phoneNumber);
	User findByUserName(String userName);
}
