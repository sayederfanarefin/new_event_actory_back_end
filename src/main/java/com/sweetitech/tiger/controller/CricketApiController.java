package com.sweetitech.tiger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sweetitech.tiger.model.cricketapi.Data;
import com.sweetitech.tiger.service.interfaces.ICricketApiService;

@RestController
@RequestMapping("/api/match")
public class CricketApiController {

	@Value("${user.privilege.read}")
	private String READ_PRIVILEGE;

	@Value("${user.privilege.write}")
	private String WRITE_PRIVILEGE;

	@Value("${user.privilege.changePassword}")
	private String CHANGE_PASSWORD_PRIVILEGE;

	@Value("${user.role.admin}")
	private String ROLE_ADMIN;

	@Value("${user.role.user}")
	private String ROLE_USER;
	
	@Autowired
	private ICricketApiService cricketApiService;
	
	
	@GetMapping("/")
	@ResponseBody
	public void showAllBanner() {

		
		cricketApiService.getMatchById("dev_season_2014_q9");
		//cricketApiService.logIn();
		//return bannerList;
	}
	
	@GetMapping("/id")
	@ResponseBody
	public ResponseEntity<Data>  showAllBanner(
			@RequestParam("matchKey") String matchKey) {

		
		return new ResponseEntity(cricketApiService.getMatchById(matchKey), HttpStatus.OK); 
		
	}
	
}