package com.sweetitech.tiger.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.sweetitech.tiger.dto.UserDto;

import com.sweetitech.tiger.exception.UserAlreadyExistException;
import com.sweetitech.tiger.model.User;
import com.sweetitech.tiger.repository.UsersRepository;
import com.sweetitech.tiger.service.interfaces.IUserService;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	private IUserService userService;

	
	@Autowired
    private UsersRepository userRepository;
	
	
//	@Autowired
//	private AuthenticationManager authenticationManager;

	public RegistrationController() {
		super();
	}

	// Registration

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> registerUserAccount(

			@RequestParam(value = "firstName", required = true) String firstName, @RequestParam(value = "lastName", required = true) String lastName,
			@RequestParam(value = "password", required = true) String password, @RequestParam(value = "email", required = true) String email,
		    @RequestParam(value = "phoneNumber", required = true) String phoneNumber, @RequestParam(value = "userName", required = true) String userName
			) {

		// always register as a user id =1
//
		UserDto accountDto = new UserDto(firstName, lastName, password, email, userName, phoneNumber, 1);


		
		try {
			final User registered = userService.registerNewUserAccount(accountDto);
			if (registered == null) {

				return new ResponseEntity<>("Some thing went wrong. Plpease check your parameters and try again.", HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<>("User successfully registered! Yay!", HttpStatus.OK);//HttpStatus.ACCEPTED;
			
		} catch (UserAlreadyExistException uae) {
			
			return new ResponseEntity<>(uae.getMessage(), HttpStatus.CONFLICT);
			

		} catch (Exception a) {
			
			return new ResponseEntity<>("Some thing went wrong. Plpease check your parameters and try again.", HttpStatus.BAD_REQUEST);
		} 

	}
	
	
	
	
	
	@RequestMapping(value = "/checkusername", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> checkUserName(@RequestParam(value = "userName", required = true) String userName) {
		
		if(userNameExist(userName)) {
			return new ResponseEntity<>("UserName Exists", HttpStatus.CONFLICT);
		}else {
			
			return new ResponseEntity<>("UserName Unique", HttpStatus.OK);
			
		}
		
	}
	
	 private boolean userNameExist(final String userName) {
		 
		 
		 User temp = userRepository.findByUserName(userName);
		 
			 
	        return temp != null;
	    }
	 
	 

	// Reset password
	// @RequestMapping(value = "/user/resetPassword", method = RequestMethod.POST)
	// @ResponseBody
	// public GenericResponse resetPassword(final HttpServletRequest request,
	// @RequestParam("email") final String userEmail) {
	// final User user = userService.findUserByEmail(userEmail);
	// if (user != null) {
	// final String token = UUID.randomUUID().toString();
	// userService.createPasswordResetTokenForUser(user, token);
	// mailSender.send(constructResetTokenEmail(getAppUrl(request),
	// request.getLocale(), token, user));
	// }
	//
	// System.out.println("---------- /user/resetPassword");
	//
	//
	// return new GenericResponse(messages.getMessage("message.resetPasswordEmail",
	// null, request.getLocale()));
	// }
	//
	// @RequestMapping(value = "/user/changePassword", method = RequestMethod.GET)
	// public String showChangePasswordPage(final Locale locale, final Model model,
	// @RequestParam("id") final long id, @RequestParam("token") final String token)
	// {
	// final String result = securityUserService.validatePasswordResetToken(id,
	// token);
	// if (result != null) {
	// model.addAttribute("message", messages.getMessage("auth.message." + result,
	// null, locale));
	// return "redirect:/login?lang=" + locale.getLanguage();
	// }
	//
	// System.out.println("---------- /user/changePassword");
	//
	//
	// return "redirect:/updatePassword.html?lang=" + locale.getLanguage();
	// }
	//
	// @RequestMapping(value = "/user/savePassword", method = RequestMethod.POST)
	// @ResponseBody
	// public GenericResponse savePassword(final Locale locale, @Valid PasswordDto
	// passwordDto) {
	// final User user = (User)
	// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	// userService.changeUserPassword(user, passwordDto.getNewPassword());
	//
	//
	// System.out.println("---------- /user/savePassword");
	//
	//
	// return new GenericResponse(messages.getMessage("message.resetPasswordSuc",
	// null, locale));
	// }
	//
	// // change user password
	// @RequestMapping(value = "/user/updatePassword", method = RequestMethod.POST)
	// @ResponseBody
	// public GenericResponse changeUserPassword(final Locale locale, @Valid
	// PasswordDto passwordDto) {
	//
	// System.out.println("---------- /user/updatePassword");
	//
	//
	//
	// final User user = userService.findUserByEmail(((User)
	// SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
	// if (!userService.checkIfValidOldPassword(user, passwordDto.getOldPassword()))
	// {
	// throw new InvalidOldPasswordException();
	// }
	// userService.changeUserPassword(user, passwordDto.getNewPassword());
	// return new GenericResponse(messages.getMessage("message.updatePasswordSuc",
	// null, locale));
	// }
	//
	//
	// private String getAppUrl(HttpServletRequest request) {
	//
	// System.out.println("---------- getAppUrl");
	//
	//
	// return "http://" + request.getServerName() + ":" + request.getServerPort() +
	// request.getContextPath();
	// }
	//
	// public void authWithHttpServletRequest(HttpServletRequest request, String
	// username, String password) {
	// try {
	// request.login(username, password);
	// } catch (ServletException e) {
	// LOGGER.error("Error while login ", e);
	// }
	// }
	//
	// public void authWithAuthManager(HttpServletRequest request, String username,
	// String password) {
	// UsernamePasswordAuthenticationToken authToken = new
	// UsernamePasswordAuthenticationToken(username, password);
	// authToken.setDetails(new WebAuthenticationDetails(request));
	// Authentication authentication =
	// authenticationManager.authenticate(authToken);
	// SecurityContextHolder.getContext().setAuthentication(authentication);
	// //
	// request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
	// SecurityContextHolder.getContext());
	// }
	//
	// public void authWithoutPassword(User user) {
	// List<Privilege> privileges = user.getRoles().stream().map(role ->
	// role.getPrivileges()).flatMap(list ->
	// list.stream()).distinct().collect(Collectors.toList());
	// List<GrantedAuthority> authorities = privileges.stream().map(p -> new
	// SimpleGrantedAuthority(p.getName())).collect(Collectors.toList());
	//
	// Authentication authentication = new UsernamePasswordAuthenticationToken(user,
	// null, authorities);
	//
	// SecurityContextHolder.getContext().setAuthentication(authentication);
	// }
}
