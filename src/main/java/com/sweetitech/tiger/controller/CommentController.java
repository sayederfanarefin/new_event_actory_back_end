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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sweetitech.tiger.model.Comment;
import com.sweetitech.tiger.model.Image;
import com.sweetitech.tiger.model.News;
import com.sweetitech.tiger.model.Privilege;
import com.sweetitech.tiger.model.Role;
import com.sweetitech.tiger.model.User;
import com.sweetitech.tiger.service.interfaces.ICommentService;
import com.sweetitech.tiger.service.interfaces.INewsService;
import com.sweetitech.tiger.service.interfaces.IUserService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

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
	private ICommentService commentService;

	@Autowired
	INewsService newsService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Comment> createComment(@Valid Principal principal,

			@RequestParam("newsId") long newsId,
			@RequestParam("commentBody") String commentBody) {
		
		Comment c = commentService.addComment(new Comment(userService.findUserByEmail(principal.getName()).getId(), commentBody, newsService.findById(newsId)));

		return new ResponseEntity(c, HttpStatus.OK);

	}

	@GetMapping("/delete")
	public @ResponseBody ResponseEntity<?> deleteComment(@Valid Principal principal,
			@RequestParam(value = "commentId", required = true) long commentId){
		if(hasPrivilege(WRITE_PRIVILEGE, userService.findUserByEmail(principal.getName()))) {
			Comment c = commentService.findById(commentId);
			commentService.deleteComment(c);
			return new ResponseEntity("Deleted!", HttpStatus.OK);
		}else {
			return new ResponseEntity("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}
	

	@GetMapping("/news/{news_id}/")
	public @ResponseBody Page<Comment> showAllNews(@PathVariable("news_id") long newsId) {

		Page<Comment> newsList = commentService.findByNews(newsService.findById(newsId), 0);
		return newsList;
	}

	@GetMapping("/news/{news_id}/page")
	public @ResponseBody Page<Comment> showAllNewsByPage(@PathVariable("news_id") long newsId,
			@RequestParam("page_id") int id) {

		Page<Comment> newsList = commentService.findByNews(newsService.findById(newsId), id);
		return newsList;
	}

	@GetMapping("/id")
	public @ResponseBody Comment showComment(@RequestParam("id") Long id) {

		Comment comment = commentService.findById(id);
		if (comment != null) {
			return comment;
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
				if (privilege1.getName().equals(privilege)) {
					flag = true;
					break;
				}
			}
		}
		return flag;

	}
}