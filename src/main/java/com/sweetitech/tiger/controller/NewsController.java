package com.sweetitech.tiger.controller;

import java.security.Principal;
import java.util.ArrayList;
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
import com.sweetitech.tiger.model.Image;
import com.sweetitech.tiger.model.News;
import com.sweetitech.tiger.model.Privilege;
import com.sweetitech.tiger.model.Role;
import com.sweetitech.tiger.model.User;
import com.sweetitech.tiger.service.interfaces.IImageService;
import com.sweetitech.tiger.service.interfaces.INewsService;
import com.sweetitech.tiger.service.interfaces.IUserService;

@RestController
@RequestMapping("/api/news")
public class NewsController {

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
	INewsService newsService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<News> createNewNews(@Valid Principal principal,

			@RequestParam(value = "title", required = true) String title, 
			@RequestParam(value = "summary", required = true) String summary,
			@RequestParam(value = "description", required = true) String description, 
			@RequestParam(value = "featruedImageId", required = true) long featruedImageId) {

		
		System.out.println(description);
		
		
		if(hasPrivilege(WRITE_PRIVILEGE, userService.findUserByEmail(principal.getName()))) {
			Image featuredImage = imageService.findById(featruedImageId);
			
			News n = newsService.addNews(new News(title, summary, description, featuredImage));
			
			
			System.out.println("returned stuff"+ n.getDescription());
			
			
			
			return new ResponseEntity(n, HttpStatus.OK);
		}else {
			return new ResponseEntity("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<News> updateNews(@Valid Principal principal,

			@RequestParam(value = "id", required = true) long id,
			@RequestParam("title") String title,
			@RequestParam("summary") String summary,
			@RequestParam("description") String description,
			@RequestParam("featruedImageId") Long featruedImageId, 
			@RequestParam(value = "additionalImages") long[] additionalImages) {
		

		if(hasPrivilege(WRITE_PRIVILEGE, userService.findUserByEmail(principal.getName()))) {
			
			
			News newsTobeUpdated = newsService.findById(id);
			 

			 if(title !=null && title !="") {
				 newsTobeUpdated.setTitle(title);
			 }
			 
			 if(summary !=null && summary !="") {
				 newsTobeUpdated.setSummary(summary);
			 }
			 
			 

			 if(description !=null && description !="") {
				 newsTobeUpdated.setDescription(description);
			 }
			 

			 
			 if(featruedImageId != null && featruedImageId !=0 ) {
				 newsTobeUpdated.setFeatruedImage(imageService.findById(featruedImageId));
			 }
			 
			 if(additionalImages != null && additionalImages.length !=0 ) {
				 
				 for(int h=0; h < additionalImages.length; h++) {
					 newsTobeUpdated.getAdditionalImage().add(imageService.findById(additionalImages[h]));
					// newsTobeUpdated.setFeatruedImage();
				 }
			 }

			
			News n = newsService.updateNews(newsTobeUpdated);
			
			return new ResponseEntity(n, HttpStatus.OK);
		}else {
			return new ResponseEntity("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}
	
	
	
	
	@RequestMapping(value = "/addAdditionalImages", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> updateNewsWithAdditionaImages(
			@Valid Principal principal, 
			@RequestParam(value = "newsId", required = true) long newsId, 
			@RequestParam(value = "images", required = true) long[] images) {
		

		if(hasPrivilege(WRITE_PRIVILEGE, userService.findUserByEmail(principal.getName()))) {
			
			List<Image> additionalImagesToBeAdded = new ArrayList<Image>();
			
			for(int i = 0 ; i < images.length;i++) {
				additionalImagesToBeAdded.add(imageService.findById(images[i]));
			}
			News n = newsService.findById(newsId);
			
			
			List<Image> previousAdditionalImages = n.getAdditionalImage();
			for(int i =0; i <previousAdditionalImages.size();i++) {
				additionalImagesToBeAdded.add(previousAdditionalImages.get(i));
			}
			
			n.setAdditionalImage(additionalImagesToBeAdded);
			newsService.updateNews(n);
			return new ResponseEntity("Additional Images Added", HttpStatus.OK);
		}else {
			return new ResponseEntity("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}
	
	
	

	// returns lastest PAGE_SIZE number of news
	@GetMapping("/")
	public @ResponseBody Page<News> showAllNews() {

		Page<News> newsList = newsService.findAllNews(0);
		return newsList;
	}

	@GetMapping("/page")
	public @ResponseBody Page<News> showAllNewsByPage(@RequestParam(value = "id", required = true) int id) {

		Page<News> newsList = newsService.findAllNews(id);

		return newsList;
	}

	@GetMapping("/id")
	public @ResponseBody News showNews(@RequestParam(value = "id", required = true) Long id) {

		News news = newsService.findById(id);
		if (news != null) {
			return news;
		} else {

			return null;
		}

	}
	
	
	@GetMapping("/search")
	public @ResponseBody Page<News> searchNews(@RequestParam(value = "title", required = true) String title) {
		
		Page<News> searchList = newsService.findByTitleContaining(title, 0);
		if (searchList != null) {
			return searchList;
		} else {

			return null;
		}

	}
	
	@GetMapping("/search/page")
	public @ResponseBody Page<News> searchNewsByPage(
			@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "pageId", required = true) int pageId
			) {
		
		Page<News> searchList = newsService.findByTitleContaining(title, pageId);
		if (searchList != null) {
			return searchList;
		} else {

			return null;
		}
	}
	
	@GetMapping("/delete")
	public @ResponseBody ResponseEntity<?> deleteNews(@Valid Principal principal,
			@RequestParam(value = "id", required = true) long id){
		if(hasPrivilege(WRITE_PRIVILEGE, userService.findUserByEmail(principal.getName()))) {
			News news = newsService.findById(id);
			newsService.deleteNews(news);
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
				if(privilege1.getName().equals(privilege)) {
					flag = true;
					break;
				}
			}
		}
		return flag;

	}
}