package com.sweetitech.tiger.controller;

import java.security.Principal;
import java.util.Iterator;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.sweetitech.tiger.dto.UserProfileDto;

import com.sweetitech.tiger.model.Image;
import com.sweetitech.tiger.model.Privilege;
import com.sweetitech.tiger.model.Role;
import com.sweetitech.tiger.model.User;
import com.sweetitech.tiger.service.interfaces.IImageService;
import com.sweetitech.tiger.service.interfaces.IUserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Value("${user.privilege.write}")
	private String WRITE_PRIVILEGE;

	@Autowired
	private IUserService userService;

	
	@Autowired
    private ModelMapper modelMapper;
	@Autowired
	private IImageService imageService;

	@GetMapping("/email")
	public String userName(Principal principal) {
		String a = principal.getName();
		return a;
	}

	@GetMapping("/")
	public UserProfileDto user(Principal principal) {
		String a = principal.getName();

		return userService.findUserProfileDtoByEmail(a);

	}

	@GetMapping("/upload/profile-picture")
	public ResponseEntity<?> uploadProfilePicture(Principal principal,
			@RequestParam(value = "imageId", required = true) long imageId) {
		String a = principal.getName();

		Image newProfilePicture = imageService.findById(imageId);

		User user = userService.findUserByEmail(a);

		user.setProfilePicture(newProfilePicture);

		userService.saveRegisteredUser(user);

		// imageService.deleteImage(user.getProfilePicture());

		return new ResponseEntity("Profile Picture updated", HttpStatus.OK);

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> updateUser(@Valid Principal principal,
			@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "lastName", required = false) String lastName,
			@RequestParam(value = "phoneNumber", required = false) String phoneNumber,
			@RequestParam(value = "profilePicture", required = false) Long profilePicture,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "userName", required = false) String userName

	) {

		User userTobeUpdated = userService.findUserByEmail(principal.getName());

		if (firstName != null && firstName != "") {
			userTobeUpdated.setFirstName(firstName);
		}

		if (lastName != null && lastName != "") {
			userTobeUpdated.setLastName(lastName);
		}

		if (phoneNumber != null && phoneNumber != "") {
			userTobeUpdated.setPhoneNumber(phoneNumber);
		}

		if (email != null && email != "") {
			userTobeUpdated.setEmail(email);
		}

		if (profilePicture != null && profilePicture != 0) {
			userTobeUpdated.setProfilePicture(imageService.findById(profilePicture));
		}

		if (userName != null && userName != "") {

			userTobeUpdated.setUserName(userName);
		}

		userService.saveRegisteredUser(userTobeUpdated);
		return new ResponseEntity("Profile updated!", HttpStatus.OK);

	}

	/////// admin parts

	@GetMapping("/showAllUsers")
	public @ResponseBody ResponseEntity<Page<UserProfileDto>> showAllUsers(Principal principal) {

		if (hasPrivilege(WRITE_PRIVILEGE, userService.findUserByEmail(principal.getName()))) {
			//Page<UserProfileDto> usersList = userService.findAllUser(0);

			Page<User> usersListPage = userService.findAllUser(0);
//			List<User> userList = usersListPage.getContent();
//			
//			List<UserProfileDto> userProfileDtoList= userList.stream()
//	          .map(user -> convertToDto(user))
//	          .collect(Collectors.toList());
			
			 //new PageImpl<UserProfileDto>(userProfileDtoList, 0, usersListPage.getTotalElements());
			
			
			Page<UserProfileDto> pp =   usersListPage.map(new Converter<User, UserProfileDto>() {
			    @Override
			    public UserProfileDto convert(User entity) {
			    //	UserProfileDto dto = new UserProfileDto();
			        // Conversion logic

			        return convertToDto(entity);
			    }
			});  //new PageImpl<UserProfileDto>(userProfileDtoList);
			
			
			
			
			
//			
//			Page<ObjectEntity> entities = objectEntityRepository.findAll(pageable);
//			Page<ObjectDto> dtoPage = entities.map(new Converter<ObjectEntity, ObjectDto>() {
//			    @Override
//			    public ObjectDto convert(ObjectEntity entity) {
//			        ObjectDto dto = new ObjectDto();
//			        // Conversion logic
//
//			        return dto;
//			    }
//			});
			
			
			
			
			
			return new ResponseEntity(pp, HttpStatus.OK);
		} else {
			return new ResponseEntity("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}


//	@GetMapping("/showAllUsers/page")
//	public @ResponseBody List<News> showAllUsersByPage(@RequestParam("id") int id) {
//
//		Page<News> newsList = userService.findAllUser(id);
//
//		return newsList.getContent();
//	}
//
//	@GetMapping("/showAllUsers/id")
//	public @ResponseBody News showUserById(@RequestParam("id") Long id) {
//
//		News news = newsService.findById(id);
//		if (news != null) {
//			return news;
//		} else {
//
//			return null;
//		}
//
//	}

	private boolean hasPrivilege(String privilege, User user) {

		boolean flag = false;

		Iterator<Role> roles = user.getRoles().iterator();
		while (roles.hasNext()) {
			Role r = roles.next();
			Iterator<Privilege> privileges = r.getPrivileges().iterator();
			while (privileges.hasNext()) {
				Privilege privilege1 = privileges.next();
				if (privilege1.getName().equals(privilege)) {
					flag = true;
					break;
				}
			}
		}
		return flag;

	}
	
	
	private UserProfileDto convertToDto(User user) {
		UserProfileDto userProfileDto = modelMapper.map(user, UserProfileDto.class);
		String sb = "";
        Iterator<Role> roles = user.getRoles().iterator();
		while (roles.hasNext()) {
			Role r = roles.next();
			sb = r.getName();
		}
		userProfileDto.setRole(sb);
		
	    return userProfileDto;
	}
}
