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

import com.sweetitech.tiger.model.Privilege;
import com.sweetitech.tiger.model.Role;
import com.sweetitech.tiger.model.TopUpdate;
import com.sweetitech.tiger.model.User;
import com.sweetitech.tiger.model.Video;
import com.sweetitech.tiger.model.VideoCategory;
import com.sweetitech.tiger.service.interfaces.ITopUpdateService;
import com.sweetitech.tiger.service.interfaces.IUserService;
import com.sweetitech.tiger.service.interfaces.IVideoService;

@RestController
@RequestMapping("/api/topUpdates")
public class TopUpdatesController {

	
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
	 private ITopUpdateService topUpdateService;	 
	 
	 
	 @GetMapping("/")
		public @ResponseBody Page<TopUpdate> showAllTopUpdate() {

			Page<TopUpdate> topUpdateList = topUpdateService.findAllTopUpdate(0);
			return topUpdateList;
		}

		@GetMapping("/page")
		public @ResponseBody List<TopUpdate> showAllVideoByPage(@RequestParam(value = "id", required = true) int id) {

			Page<TopUpdate> topUpdateList = topUpdateService.findAllTopUpdate(id);

			return topUpdateList.getContent();
		}
	 
	 @GetMapping("/id")
		public @ResponseBody TopUpdate showVideo(@RequestParam(value = "id", required = true) Long id) {

		 TopUpdate topUpdate = topUpdateService.findById(id);
			if (topUpdate != null) {
				return topUpdate;
			} else {

				return null;
			}

		}

}
