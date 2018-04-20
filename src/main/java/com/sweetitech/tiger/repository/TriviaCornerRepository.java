package com.sweetitech.tiger.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sweetitech.tiger.model.TriviaCorner;

@Repository("triviaCornerRepository")
public interface TriviaCornerRepository extends CrudRepository<TriviaCorner, Integer>{
	
	 public List<TriviaCorner> findAllByOrderByLiveAtAsc();
	 
	 
	 public TriviaCorner findById(Long id);
	 
	 public TriviaCorner save(TriviaCorner triviaCorner);
	 
	 
	// @Query("from Auction a join a.category c where c.name=:categoryName")
	 @Query("SELECT e FROM TriviaCorner e WHERE e.liveAt < :maxDate AND e.liveAt > :minDate")
	 public Page<TriviaCorner> findByLiveAt(@Param("maxDate") Date maxDate, @Param("minDate") Date minDate, Pageable pageReguest);
	
}

