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

import com.sweetitech.tiger.model.Privilege;
import com.sweetitech.tiger.model.Role;
import com.sweetitech.tiger.model.User;
import com.sweetitech.tiger.model.ecommerce.GroupVariant;
import com.sweetitech.tiger.model.ecommerce.ProductGroup;
import com.sweetitech.tiger.service.interfaces.IGroupService;
import com.sweetitech.tiger.service.interfaces.IUserService;

@RestController
@RequestMapping("/api/product/group")
public class GroupController {

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
	private IGroupService groupService;


	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ProductGroup> createProductGroup(@Valid Principal principal,
			@RequestParam(value = "groupName", required = true) String groupName, 
			@RequestParam(value = "variantName", required = true) String variantName[]) {
		

		if(hasPrivilege(WRITE_PRIVILEGE, userService.findUserByEmail(principal.getName()))) {
			
			ProductGroup productGroup = new ProductGroup();
			productGroup.setGroupName(groupName);
			List <GroupVariant> groupVariants = new ArrayList<GroupVariant>();
			
			for(int i=0;i<variantName.length;i++) {
				groupVariants.add(new GroupVariant(variantName[i]));
			}
			
			productGroup.setGroupVariants(groupVariants);
			
			ProductGroup productGroupSaved = groupService.addProductGroup(productGroup);
			
			return new ResponseEntity(productGroupSaved, HttpStatus.OK);
		}else {
			return new ResponseEntity("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}
	
	@GetMapping("/page")
	public @ResponseBody Page<ProductGroup> showAllProductGroups(@RequestParam(value = "pageId", required = true) int pageId) {
		Page<ProductGroup> productGroups = groupService.findAllProductGroup(pageId);
		return productGroups;
	}

	@GetMapping("/")
	public @ResponseBody Page<ProductGroup> showProductGroupsByPage() {
		Page<ProductGroup> productGroups = groupService.findAllProductGroup(0);
		return productGroups;
	}
	
	@GetMapping("/id")
	public @ResponseBody ProductGroup showProductGroupsById(@RequestParam(value = "id", required = true) long id) {
		return groupService.findById(id);
	}
	
	@GetMapping("/{id}/variant")
	public @ResponseBody List<GroupVariant> ProductVariantGroupsById(@PathVariable long id) {
		return groupService.findById(id).getGroupVariants();
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