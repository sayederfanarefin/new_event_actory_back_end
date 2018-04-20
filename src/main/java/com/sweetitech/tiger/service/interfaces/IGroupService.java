package com.sweetitech.tiger.service.interfaces;

import org.springframework.data.domain.Page;

import com.sweetitech.tiger.model.Video;
import com.sweetitech.tiger.model.ecommerce.ProductGroup;



public interface IGroupService {

	ProductGroup addProductGroup(ProductGroup productGroup);
	Page<ProductGroup> findAllProductGroup(int page);
	ProductGroup findById(Long id);
//	ProductGroup findByGroupName(String groupName);
	
	ProductGroup updateProductGroup(ProductGroup productGroup);
	
	void deleteProductGroup(ProductGroup roductGroup);
}
