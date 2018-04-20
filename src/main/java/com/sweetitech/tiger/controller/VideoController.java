package com.sweetitech.tiger.controller;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sweetitech.tiger.model.Image;
import com.sweetitech.tiger.model.News;
import com.sweetitech.tiger.model.Privilege;
import com.sweetitech.tiger.model.Role;
import com.sweetitech.tiger.model.User;
import com.sweetitech.tiger.model.Video;
import com.sweetitech.tiger.model.VideoCategory;
import com.sweetitech.tiger.service.interfaces.IUserService;
import com.sweetitech.tiger.service.interfaces.IVideoCategoryService;
import com.sweetitech.tiger.service.interfaces.IVideoService;

@RestController
@RequestMapping("/api/video")
public class VideoController {

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
	private IVideoCategoryService videoCategoryService;

	@Autowired
	IVideoService videoService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Video> createNewVideo(@Valid Principal principal,

			@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "url", required = true) String url,
			@RequestParam(value = "description", required = true) String description,
			@RequestParam(value = "category", required = true) String category) {

		if (hasPrivilege(WRITE_PRIVILEGE, userService.findUserByEmail(principal.getName()))) {

			VideoCategory vc = videoCategoryService.findByName(category);

			Video n = videoService.addVideo(new Video(title, url, description, vc));

			return new ResponseEntity(n, HttpStatus.OK);
		} else {
			return new ResponseEntity("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}

	@GetMapping("/")
	public @ResponseBody Page<Video> showAllNews() {

		Page<Video> newsList = videoService.findAllVideos(0);
		return newsList;
	}

	@GetMapping("/page")
	public @ResponseBody Page<Video> showAllVideoByPage(@RequestParam(value = "id", required = true) int id) {

		Page<Video> videoList = videoService.findAllVideos(id);

		return videoList;
	}

	@GetMapping("/id")
	public @ResponseBody Video showVideo(@RequestParam(value = "id", required = true) Long id) {

		Video news = videoService.findById(id);
		if (news != null) {
			return news;
		} else {

			return null;
		}

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Video> updateVideo(@Valid Principal principal,
			
			
			
			@RequestParam(value = "id" , required = true) Long id,
			@RequestParam("title") String title, @RequestParam("url") String url,
			@RequestParam("description") String description, @RequestParam("category") String category) {

		if (hasPrivilege(WRITE_PRIVILEGE, userService.findUserByEmail(principal.getName()))) {
			


			Video videoTobeUpdated = videoService.findById(id);
			 

			 if(title !=null && title !="") {
				 videoTobeUpdated.setTitle(title);
			 }
			 
			 if(url !=null && url !="") {
				 videoTobeUpdated.setUrl(url);
			 }
			 
			 

			 if(description !=null && description !="") {
				 videoTobeUpdated.setDescription(description);
			 }
			 


			 if(category != null && category != "" ) {
				 
				 videoTobeUpdated.setVideoCategory(videoCategoryService.findByName(category));
			 }

			
			 Video n = videoService.updateVideo(videoTobeUpdated);
			
			return new ResponseEntity(n, HttpStatus.OK);
		} else {
			return new ResponseEntity("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}

	@GetMapping("/search")
	public @ResponseBody Page<Video> searchVideo(@RequestParam(value = "title", required = true) String title) {

		Page<Video> searchList = videoService.findByTitleContaining(title, 0);
		if (searchList != null) {
			return searchList;
		} else {

			return null;
		}

	}
	
	@GetMapping("/search/page")
	public @ResponseBody Page<Video> searchVideo(
			@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "pageId", required = true) int pageId) {

		Page<Video> searchList = videoService.findByTitleContaining(title, pageId);
		if (searchList != null) {
			return searchList;
		} else {

			return null;
		}

	}
	
	@GetMapping("/delete")
	public @ResponseBody ResponseEntity<?> deleteVideo(@Valid Principal principal,
			@RequestParam(value = "id", required = true) long id){
		if(hasPrivilege(WRITE_PRIVILEGE, userService.findUserByEmail(principal.getName()))) {
			Video video = videoService.findById(id);
			videoService.deleteVideo(video);
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
