package com.sweetitech.tiger.repository.ecommerce;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sweetitech.tiger.model.ecommerce.Product;
import com.sweetitech.tiger.model.ecommerce.Ptag;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Long> {
	Product findById(long id);

	List<Product> findAllProductByPtags(List<Ptag> ptags);
	
	Page<Product> findAllProductByPtags_Tag(String tag, Pageable pageable);
	Page<Product> findAllProductByNameContaining(String name, Pageable pageable);
}