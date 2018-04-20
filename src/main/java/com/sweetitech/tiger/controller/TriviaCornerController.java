package com.sweetitech.tiger.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sweetitech.tiger.config.Constants;
import com.sweetitech.tiger.model.Image;
import com.sweetitech.tiger.model.Privilege;
import com.sweetitech.tiger.model.Role;
import com.sweetitech.tiger.model.TriviaCorner;
import com.sweetitech.tiger.model.User;
import com.sweetitech.tiger.service.interfaces.IImageService;
import com.sweetitech.tiger.service.interfaces.ITriviaCornerService;
import com.sweetitech.tiger.service.interfaces.IUserService;

@RestController
@RequestMapping("/api/triviaCorner")
public class TriviaCornerController {

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
	private IUserService userService;
	
	@Autowired
	private IImageService imageService;

	@Autowired
	ITriviaCornerService triviaCornerService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<TriviaCorner> createNewNews(@Valid Principal principal,


			@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "liveAt", required = true) String liveAt,
			@RequestParam(value = "description", required = true) String description, 
			@RequestParam(value = "imageId", required = true) long imageId) {
		

		if(hasPrivilege(WRITE_PRIVILEGE, userService.findUserByEmail(principal.getName()))) {
			Image image = imageService.findById(imageId);
			
			TriviaCorner n = triviaCornerService.addTriviaCorner(new TriviaCorner(title, description,convertToDate(liveAt),  image));
			
			return new ResponseEntity(n, HttpStatus.OK);
		}else {
			return new ResponseEntity("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}
	
	
	

	// returns lastest PAGE_SIZE number of TriviaCorner
	@GetMapping("/")
	public @ResponseBody Page<TriviaCorner> showAllTriviaCorner(@RequestParam(value = "liveAt", required = true) String liveAt) {

		//date format: yyyy-MM-dd-HH:mm:ss in utc 00
		Page<TriviaCorner> newsList = triviaCornerService.findByLiveAt(convertToDate(liveAt), 0);
			return newsList;
	}
	
	@GetMapping("/page")
	public @ResponseBody Page<TriviaCorner> showAllTriviaCornerByPage(
			@RequestParam(value = "liveAt", required = true) String liveAt,
			@RequestParam(value = "pageId", required = true) int pageId
			) {

		//date format: yyyy-MM-dd-HH:mm:ss in utc 00
		Page<TriviaCorner> newsList = triviaCornerService.findByLiveAt(convertToDate(liveAt), pageId);
			return newsList;
	}

	
	@GetMapping("/id")
	public @ResponseBody TriviaCorner showTriviaCorner(@RequestParam(value = "id", required = true) Long id) {

		TriviaCorner triviaCorner = triviaCornerService.findById(id);
		if (triviaCorner != null) {
			return triviaCorner;
		} else {

			return null;
		}

	}
	
	
	@GetMapping("/delete")
	public @ResponseBody ResponseEntity<?> deleteVideo(@Valid Principal principal,
			@RequestParam(value = "id", required = true) long id){
		if(hasPrivilege(WRITE_PRIVILEGE, userService.findUserByEmail(principal.getName()))) {
			TriviaCorner triviaCorner = triviaCornerService.findById(id);
			triviaCornerService.deleteTriviaCorner(triviaCorner);
			return new ResponseEntity("Deleted", HttpStatus.OK);
		}else {
			return new ResponseEntity("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	private Date convertToDate(String dateString) {

		//date format: yyyy-MM-dd-HH:mm:ss in utc 00
		DateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
		Date date;
		try {
			date = formatter.parse(dateString);
			return date;
			
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
	
	
	private boolean hasPrivilege(String privilege, User user) {
		
		boolean flag = false;
		
		Iterator<Role> roles = user.getRoles().iterator();
		while (roles.hasNext()) {
			Role r = roles.next();
			Iterator<Privilege> privileges = r.getPrivileges().iterator();
			while (privileges.hasNext()) {
				Privilege privilege1 = privileges.next();
				if(privilege1.getName().equals(privilege)) {
					flag = true;
					break;
				}
			}
		}
		return flag;

	}
}