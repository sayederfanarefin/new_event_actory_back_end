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

import com.sweetitech.tiger.model.Comment;
import com.sweetitech.tiger.model.GalleryImage;
import com.sweetitech.tiger.model.Image;
import com.sweetitech.tiger.model.News;
import com.sweetitech.tiger.model.Privilege;
import com.sweetitech.tiger.model.Role;
import com.sweetitech.tiger.model.User;
import com.sweetitech.tiger.service.interfaces.IGalleryImageService;
import com.sweetitech.tiger.service.interfaces.IImageService;
import com.sweetitech.tiger.service.interfaces.IUserService;

@RestController
@RequestMapping("/api/gallery")
public class GalleryImageController {

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
	IGalleryImageService galleryImageService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<GalleryImage> createGalleryImage(@Valid Principal principal,

			@RequestParam(value = "title", required = true)  String title, 
			@RequestParam(value = "description", required = true)  String description,
			@RequestParam(value = "imageId", required = true)  long imageId) {

		if (hasPrivilege(WRITE_PRIVILEGE, userService.findUserByEmail(principal.getName()))) {
			Image image = imageService.findById(imageId);

			GalleryImage galleryImage = galleryImageService
					.addGalleryImage(new GalleryImage(title, description, image));

			return new ResponseEntity(galleryImage, HttpStatus.OK);
		} else {
			return new ResponseEntity("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}

	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<GalleryImage> updateGalleryImage(@Valid Principal principal,

			@RequestParam("title") String title, @RequestParam("description") String description,
			@RequestParam("imageId") Long imageId, 
			@RequestParam(value = "id", required = true) Long id) {

		if (hasPrivilege(WRITE_PRIVILEGE, userService.findUserByEmail(principal.getName()))) {
			
			GalleryImage galleryImageToBeUpdated  = galleryImageService.findById(id);
			if(title != null && title != "") {
				galleryImageToBeUpdated.setTitle(title);
			}
			if(description != null && description != "") {
				galleryImageToBeUpdated.setDescription(description);
			}
			if(imageId != null && imageId != 0) {
				galleryImageToBeUpdated.setImage(imageService.findById(id));
			}
			
			GalleryImage galleryImage  =galleryImageService.updateGalleryImage(galleryImageToBeUpdated);
			
			return new ResponseEntity(galleryImage, HttpStatus.OK);
		} else {
			return new ResponseEntity("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}
	
	
	// returns lastest PAGE_SIZE number of galleryImag
	@GetMapping("/")
	public @ResponseBody Page<GalleryImage> showAllGalleryImage() {

		Page<GalleryImage> galleryImageList = galleryImageService.findAllGalleryImage(0);
		return galleryImageList;
	}

	
	
	@GetMapping("/page")
	public @ResponseBody Page<GalleryImage> showAllGalleryImageByPage(@RequestParam(value = "id", required = true) int id) {

		Page<GalleryImage> galleryImageList = galleryImageService.findAllGalleryImage(id);

		return galleryImageList;
	}

	@GetMapping("/id")
	public @ResponseBody GalleryImage showGalleryImage(@RequestParam(value = "id", required = true) Long id) {

		GalleryImage galleryImage = galleryImageService.findById(id);
		if (galleryImage != null) {
			return galleryImage;
		} else {

			return null;
		}

	}
	
	@GetMapping("/delete")
	public @ResponseBody ResponseEntity<?> deleteComment(@Valid Principal principal,
			@RequestParam(value = "id", required = true) long id){
		if(hasPrivilege(WRITE_PRIVILEGE, userService.findUserByEmail(principal.getName()))) {
			GalleryImage galleryImage = galleryImageService.findById(id);
			galleryImageService.deleteGalleryImage(galleryImage);
			return new ResponseEntity("Deleted", HttpStatus.OK);
		}else {
			return new ResponseEntity("You do not have the permission to do so", HttpStatus.FORBIDDEN);
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
				if (privilege1.getName().equals(privilege)) {
					flag = true;
					break;
				}
			}
		}
		return flag;

	}
}