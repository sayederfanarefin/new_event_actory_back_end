package com.sweetitech.tiger.controller;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;

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

import com.sweetitech.tiger.model.Banner;
import com.sweetitech.tiger.model.BannerSize;
import com.sweetitech.tiger.model.Privilege;
import com.sweetitech.tiger.model.Role;
import com.sweetitech.tiger.model.User;
import com.sweetitech.tiger.repository.BannerSizeRepository;
import com.sweetitech.tiger.service.interfaces.IBannerService;
import com.sweetitech.tiger.service.interfaces.IImageService;
import com.sweetitech.tiger.service.interfaces.IUserService;

@RestController
@RequestMapping("/api/order")
public class OrderController {

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
	IBannerService bannerService;
	
	@Autowired
	BannerSizeRepository bannerSizeRepository;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Banner> createBanner(@Valid Principal principal,
			@RequestParam("url") String url, @RequestParam("imageId") long imageId, @RequestParam("bannerSizeId") long bannerSizeId) {

		
			return new ResponseEntity("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		

	}
	
	
	@GetMapping("/")
	public @ResponseBody Page<Banner> showAllBanner() {

		Page<Banner> bannerList = bannerService.findAllBanner(0);
		return bannerList;
	}

	@GetMapping("/id")
	public @ResponseBody Banner showBanner(@RequestParam("id") Long id) {

		Banner banner = bannerService.findById(id);
		if (banner != null) {
			return banner;
		} else {

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