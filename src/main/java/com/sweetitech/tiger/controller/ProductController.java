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

import com.sweetitech.tiger.model.Image;
import com.sweetitech.tiger.model.Privilege;
import com.sweetitech.tiger.model.Role;
import com.sweetitech.tiger.model.User;
import com.sweetitech.tiger.model.ecommerce.Product;
import com.sweetitech.tiger.model.ecommerce.ProductGroup;
import com.sweetitech.tiger.model.ecommerce.Ptag;
import com.sweetitech.tiger.repository.ecommerce.GroupRepository;
import com.sweetitech.tiger.repository.ecommerce.PTagRepository;
import com.sweetitech.tiger.service.interfaces.IImageService;
import com.sweetitech.tiger.service.interfaces.IProductService;
import com.sweetitech.tiger.service.interfaces.IProductTagService;
import com.sweetitech.tiger.service.interfaces.IUserService;

@RestController
@RequestMapping("/api/product")

public class ProductController {

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
	private IProductTagService ptagService;

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private IProductService productService;

	@Autowired
	private IImageService imageService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Product> createProduct(@Valid Principal principal,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "price", required = true) String price,
			@RequestParam(value = "description", required = true) String description, 
			@RequestParam(value = "groupIds", required = true) long[] groupIds,
			@RequestParam(value = "imageIds", required = true) long[] imageIds, 
			@RequestParam(value = "tags", required = true) String[] tags) {
		if (hasPrivilege(WRITE_PRIVILEGE, userService.findUserByEmail(principal.getName()))) {

			Product product = new Product(name, price, description);

			List<Image> images = new ArrayList<Image>();
			for (int i = 0; i < imageIds.length; i++) {
				images.add(imageService.findById(imageIds[i]));
			}

			product.setImages(images);

			for (int i = 0; i < groupIds.length; i++) {
				ProductGroup productGroup = groupRepository.findById(groupIds[i]);

				product.getGroup().add(productGroup);
				productGroup.getProduct().add(product);
			}

			// List<Ptag> ptags = new ArrayList<Ptag>();
			for (int i = 0; i < tags.length; i++) {
				Ptag tempPtag = ptagService.findByTag(tags[i]);
				if( tempPtag == null) {
					tempPtag = new Ptag(tags[i]);
				}
				product.getTags().add(tempPtag);
				tempPtag.getProducts().add(product);
			}

			Product productSaved = productService.addProduct(product);

			return new ResponseEntity<Product>(productSaved, HttpStatus.OK);
		} else {
			return new ResponseEntity("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Product> updateProduct(@Valid Principal principal,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "price", required = false) String price,
			@RequestParam(value = "description", required = false) String description, 
			@RequestParam(value = "groupIds", required = false) long[] groupIds,
			@RequestParam(value = "imageIds", required = false) long[] imageIds, 
			@RequestParam(value = "tags", required = false) String[] tags,
			@RequestParam(value = "id", required = true) Long id) {
		if (hasPrivilege(WRITE_PRIVILEGE, userService.findUserByEmail(principal.getName()))) {
			
			
			Product product = productService.findById(id);
			
			if(name != null && name !="") {
				product.setName(name);
			}
			
			if(price != null && price !="") {
				product.setPrice(price);
			}
			if(description != null && description !="") {
				product.setDescription(description);
			}
			if(imageIds != null && imageIds.length !=0) {
				
				List<Image> images = new ArrayList<Image>();
				for(int x=0; x < imageIds.length;x++) {
					images.add(imageService.findById(imageIds[x]));
				}
				product.setImages(images);
			}

			
//			if(groupIds != null && groupIds.length !=0) {
//				
//				List<ProductGroup> productGroup = new ArrayList<Image>();
//				for(int x=0; x < imageIds.length;x++) {
//					images.add(imageService.findById(groupIds[x]));
//				}
//				product.setImages(images);
//			}
//
//			
//			for (int i = 0; i < groupIds.length; i++) {
//				ProductGroup productGroup = groupRepository.findById(groupIds[i]);
//
//				product.getGroup().add(productGroup);
//				productGroup.getProduct().add(product);
//			}
//
//			// List<Ptag> ptags = new ArrayList<Ptag>();
//			for (int i = 0; i < tags.length; i++) {
//				Ptag tempPtag = ptagService.findByTag(tags[i]);
//				if( tempPtag == null) {
//					tempPtag = new Ptag(tags[i]);
//				}
//				product.getTags().add(tempPtag);
//				tempPtag.getProducts().add(product);
//			}

			Product productSaved = productService.addProduct(product);

			return new ResponseEntity<Product>(productSaved, HttpStatus.OK);
		} else {
			return new ResponseEntity("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}

	}
	
	@GetMapping("/delete")
	public @ResponseBody ResponseEntity<?> deleteProduct(@Valid Principal principal, @RequestParam(value = "id", required = false) Long id) {
		if (hasPrivilege(WRITE_PRIVILEGE, userService.findUserByEmail(principal.getName()))) {
			Product product = productService.findById(id);
			productService.deleteProduct(product);
		
			return new ResponseEntity("Deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity("You do not have the permission to do so", HttpStatus.FORBIDDEN);
		}
	}

	@GetMapping("/")
	public @ResponseBody Page<Product> showAllProductGroups() {

		Page<Product> products = productService.findAllProduct(0);
		return products;
	}

	@GetMapping("/page")
	public @ResponseBody Page<Product> showAllProductsByPage(@RequestParam(value = "pageId", required = true) int id) {

		Page<Product> newsList = productService.findAllProduct(id);

		return newsList;
	}

	@GetMapping("/id")
	public @ResponseBody Product showProduct(@RequestParam(value = "id", required = true) Long id) {

		Product product = productService.findById(id);
		if (product != null) {
			return product;
		} else {

			return null;
		}

	}

	@GetMapping("/tags/")
	 public @ResponseBody Page<Ptag> showAllTags() {
		return ptagService.findAllByPage(0);
	 }
	@GetMapping("/tags/page")
	 public @ResponseBody Page<Ptag> showAllTagsByPage(@RequestParam(value = "id", required = true)  int id) {
		return ptagService.findAllByPage(id);
	 }
	
	@GetMapping("/tags/id")
	 public @ResponseBody Ptag showTagById(@RequestParam(value = "id", required = true) long id) {
		return ptagService.findById(id);
	 }
	

	@GetMapping("/search")
	public @ResponseBody Page<Product> searchProduct(@RequestParam(value = "keyword", required = true) String keyword) {
		return productService.findAllProductByNameContaining(keyword,0);

	}
	
	@GetMapping("/search/page")
	public @ResponseBody Page<Product> searchProductByPage(
			@RequestParam(value = "keyword", required = true) String keyword,
			@RequestParam(value = "pageId", required = true) int pageId
			) {
		return productService.findAllProductByNameContaining(keyword, pageId);

	}
	
	@GetMapping("/search/tag")
	public @ResponseBody Page<Product> searchProductByTag(@RequestParam(value = "tag", required = true) String tag) {
		return productService.findAllProductByNameContaining(tag,0);

	}
	
	@GetMapping("/search/tag/page")
	public @ResponseBody Page<Product> searchProductByTagByPage(
			@RequestParam(value = "tag", required = true) String tag,
			@RequestParam(value = "pageId", required = true) int pageId
			) {
		return productService.findAllProductByPtags_Tag(tag, pageId);

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