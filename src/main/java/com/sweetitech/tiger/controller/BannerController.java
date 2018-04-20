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
import com.sweetitech.tiger.model.Comment;
import com.sweetitech.tiger.model.Image;
import com.sweetitech.tiger.model.Privilege;
import com.sweetitech.tiger.model.Role;
import com.sweetitech.tiger.model.User;
import com.sweetitech.tiger.repository.BannerSizeRepository;
import com.sweetitech.tiger.service.interfaces.IBannerService;
import com.sweetitech.tiger.service.interfaces.IImageService;
import com.sweetitech.tiger.service.interfaces.IUserService;

@RestController
@RequestMapping("/api/banner")
public class BannerController {

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
			@RequestParam(value = "url", required = true) String url, 
			@RequestParam(value = "imageId", required = true) long imageId, 
			@RequestParam(value = "bannerSizeId", required = true) long bannerSizeId) {

		if(hasPrivilege(WRITE_PRIVILEGE, userService.findUserByEmail(principal.getName()))) {
			Image image = imageService.findById(imageId);
			
			BannerSize bannerSize = bannerSizeRepository.findById(bannerSizeId);
			Banner banner = bannerService.addBanner(new Banner(bannerSize, url , image));
			
			return new ResponseEntity(banner, HttpStatus.OK);
		}else {
			return new ResponseEntity("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Banner> updateBanner(@Valid Principal principal,
			@RequestParam("url") String url, 
			@RequestParam("imageId") Long imageId, 
			@RequestParam("bannerSizeId") Long bannerSizeId, 
			@RequestParam(value = "id", required = true) Long id) {

		if(hasPrivilege(WRITE_PRIVILEGE, userService.findUserByEmail(principal.getName()))) {
			Image image = imageService.findById(imageId);
			
			BannerSize bannerSize = bannerSizeRepository.findById(bannerSizeId);
			
			Banner toBeUpdated = bannerService.findById(id);
			 
			 if(url !=null && url !="") {
				 toBeUpdated.setUrl(url);
			 }
			 
			 if(imageId !=null && imageId != 0) {
				 toBeUpdated.setImage(imageService.findById(imageId));
			 }
			 
			 if(bannerSizeId !=null && bannerSizeId != 0) {
				 toBeUpdated.setBannerSize(bannerSizeRepository.findById(bannerSizeId));
			 }

			Banner banner = bannerService.updateBanner(toBeUpdated);
			
			return new ResponseEntity(banner, HttpStatus.OK);
		}else {
			return new ResponseEntity("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}
	
	@GetMapping("/delete")
	public @ResponseBody ResponseEntity<?> deleteBanner(@Valid Principal principal,
			@RequestParam(value = "id", required = true) long id){
		if(hasPrivilege(WRITE_PRIVILEGE, userService.findUserByEmail(principal.getName()))) {
			Banner banner = bannerService.findById(id);
			bannerService.deleteBanner(banner);
			return new ResponseEntity("Deleted", HttpStatus.OK);
		}else {
			return new ResponseEntity("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}
	
	@GetMapping("/bySize")
	public @ResponseBody Page<Banner> showAllBannersBySize(
			@RequestParam(value = "bannerSizeId", required = true) long bannerSizeId) {
		return bannerService.findAllBannerByBannerSize(bannerSizeId, 0);
	}
	
	@GetMapping("/bySize/page")
	public @ResponseBody Page<Banner> showAllBannersBySizeByPage(
			@RequestParam(value = "bannerSizeId", required = true)  long bannerSizeId, 
			@RequestParam(value = "pageId", required = true)  int pageId) {
		return bannerService.findAllBannerByBannerSize(bannerSizeId, pageId);
	}

	
	@GetMapping("/sizes/")
	public @ResponseBody List<BannerSize> showAllBannerSizes() {
		return bannerSizeRepository.findAll();
	}
	
	
	
	
	@GetMapping("/")
	public @ResponseBody Page<Banner> showAllBanner() {

		Page<Banner> bannerList = bannerService.findAllBanner(0);
		return bannerList;
	}

	@GetMapping("/page")
	public @ResponseBody Page<Banner> showAllBannerByPage(@RequestParam(value = "id", required = true) int id) {

		Page<Banner> galleryImageList = bannerService.findAllBanner(id);

		return galleryImageList;
	}

	@GetMapping("/id")
	public @ResponseBody Banner showBanner(@RequestParam(value = "id", required = true) Long id) {

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