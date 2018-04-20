package com.sweetitech.tiger.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetitech.tiger.model.TopUpdate;


public interface TopUpdateRepository extends JpaRepository<TopUpdate, Long> {

	TopUpdate findById (long id);
	
	TopUpdate findBy (Date date);
	
	List<TopUpdate> findAll();
	
}
