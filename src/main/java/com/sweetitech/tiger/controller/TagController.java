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
import com.sweetitech.tiger.model.GalleryImage;
import com.sweetitech.tiger.model.Privilege;
import com.sweetitech.tiger.model.Role;
import com.sweetitech.tiger.model.Tag;
import com.sweetitech.tiger.model.User;
import com.sweetitech.tiger.service.interfaces.IGalleryImageService;
import com.sweetitech.tiger.service.interfaces.ITagService;
import com.sweetitech.tiger.service.interfaces.IUserService;

@RestController
@RequestMapping("/api/tag")
public class TagController {

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
	private IGalleryImageService galleryImageService;

	@Autowired
	ITagService tagService;

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Banner> createTag(@Valid Principal principal,

			@RequestParam("galleryImageId") long galleryImageId, @RequestParam("tag") String tag) {

		if (hasPrivilege(WRITE_PRIVILEGE, userService.findUserByEmail(principal.getName()))) {
			List<GalleryImage> galleryImages = new ArrayList<GalleryImage>();

			galleryImages.add(galleryImageService.findById(galleryImageId));

			Tag tago = tagService.addTag(new Tag(tag, galleryImages));

			return new ResponseEntity(tago, HttpStatus.OK);
		} else {
			return new ResponseEntity("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}

	// returns lastest PAGE_SIZE number of galleryImag
	@GetMapping("/")
	public @ResponseBody List<Tag> showAllTag() {

		List<Tag> tagList = tagService.findAll();
		return tagList;
	}

	@GetMapping("/id")
	public @ResponseBody Tag showTag(@RequestParam("id") Long id) {

		Tag tag = tagService.findById(id);
		if (tag != null) {
			return tag;
		} else {

			return null;
		}

	}

	@RequestMapping(value = "/addTagsToGalleryImages", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<Tag>> addTagsToGalleryImages(@RequestParam("galleryImageId") Long galleryImageId,
			@RequestParam("tags") String tags[]) {

		List<Tag> taaags = new ArrayList<Tag>();

		GalleryImage galleryImage = galleryImageService.findById(galleryImageId);

		for (int i = 0; i < tags.length; i++) {
			Tag t = tagService.findByTag(tags[i]);
			if (t != null) {

				List<GalleryImage> galleryImages = new ArrayList<GalleryImage>();
				for (int j = 0; j < t.getGalleryImages().size(); j++) {
					galleryImages.add(t.getGalleryImages().get(j));
				}
				t.setGalleryImages(galleryImages);
				tagService.updateTag(t);
				taaags.add(t);

			} else {

				List<GalleryImage> galleryImages = new ArrayList<GalleryImage>();
				galleryImages.add(galleryImage);
				Tag tt = tagService.addTag(new Tag(tags[i], galleryImages));
				taaags.add(tt);
			}
		}
		return new ResponseEntity(taaags, HttpStatus.OK);

	}

	@RequestMapping(value = "/addTagsToMultimpleGalleryImages", method = RequestMethod.POST)
	@ResponseBody
	public Tag addTagsToMultimpleGalleryImages(@RequestParam("galleryImages") Long galleryImages[],
			@RequestParam("tagId") long tagId) {

		Tag tag = tagService.findById(tagId);
		if (tag != null) {

			for (int i = 0; i < galleryImages.length; i++) {
				tag.getGalleryImages().add(galleryImageService.findById(galleryImages[i]));
			}

			return tagService.updateTag(tag);
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