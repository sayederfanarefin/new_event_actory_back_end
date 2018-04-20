package com.sweetitech.tiger.service.interfaces;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.sweetitech.tiger.model.TriviaCorner;
import com.sweetitech.tiger.model.Video;


public interface ITriviaCornerService {

	TriviaCorner addTriviaCorner(TriviaCorner triviaCorner);
	
	TriviaCorner findById(Long id);
	
	public Page<TriviaCorner> findByLiveAt(Date date, int page) ;
	
	TriviaCorner updateTriviaCorner(TriviaCorner triviaCorner);
	
	void deleteTriviaCorner(TriviaCorner triviaCorner);
}
