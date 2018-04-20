package com.sweetitech.tiger.repository.ecommerce;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sweetitech.tiger.model.ecommerce.ProductGroup;

@Repository("groupRepository")
public interface GroupRepository extends JpaRepository<ProductGroup, Long> {
	
	ProductGroup findById(long id);
	
}