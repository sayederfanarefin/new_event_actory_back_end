package com.sweetitech.tiger.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sweetitech.tiger.config.Constants;
import com.sweetitech.tiger.model.TriviaCorner;
import com.sweetitech.tiger.repository.TriviaCornerRepository;
import com.sweetitech.tiger.service.interfaces.ITriviaCornerService;


@Service
@Transactional
public class TriviaCornerService implements ITriviaCornerService {

	@Autowired
	TriviaCornerRepository triviaCornerRepository;
	
	
	
	@Override
	public TriviaCorner addTriviaCorner(TriviaCorner triviaCorner) {
		// TODO Auto-generated method stub
		 return triviaCornerRepository.save(triviaCorner);
		
	}
	
	@Override
	public TriviaCorner findById(Long id) {
		
		return triviaCornerRepository.findById(id);
	}
	@Override
	public TriviaCorner updateTriviaCorner(TriviaCorner triviaCorner) {
		return triviaCornerRepository.save(triviaCorner);
	}
	
	
	@Override
	public Page<TriviaCorner> findByLiveAt(Date date, int page) {
		// TODO Auto-generated method stub
		
		 DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
		
	        System.out.println("date : " + dateFormat.format(date));

	        // convert date to localdatetime
	        LocalDateTime localDateTimePlus = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	        
	        LocalDateTime localDateTimeMinus = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	        
	      //  System.out.println("localDateTime : " + dateFormat8.format(localDateTime));

	       
	        localDateTimePlus = localDateTimePlus.plusDays(7);
	        
	        localDateTimeMinus =  localDateTimeMinus.minusDays(7);
	        
	        //localDateTime = localDateTime.plusHours(1).plusMinutes(2).minusMinutes(1).plusSeconds(1);

	        // convert LocalDateTime to date
	        Date datePlusDay = Date.from(localDateTimePlus.atZone(ZoneId.systemDefault()).toInstant());
	        
	        Date dateMinusDay = Date.from(localDateTimeMinus.atZone(ZoneId.systemDefault()).toInstant());

//	        System.out.println("\nOutput : datePlusDay: " + dateFormat.format(datePlusDay));
//	        System.out.println("\nOutput : dateMinusDay: " + dateFormat.format(dateMinusDay));

	        PageRequest request =
		            new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
	        
		return triviaCornerRepository.findByLiveAt( datePlusDay, dateMinusDay, request);
	}

	@Override
	public void deleteTriviaCorner(TriviaCorner triviaCorner) {
		triviaCornerRepository.delete(triviaCorner);
		
	}
	
	

}
