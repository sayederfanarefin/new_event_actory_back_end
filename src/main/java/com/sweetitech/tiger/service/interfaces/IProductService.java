package com.sweetitech.tiger.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sweetitech.tiger.model.ecommerce.Product;
import com.sweetitech.tiger.model.ecommerce.Ptag;

public interface IProductService {

	Product addProduct(Product product);

	Page<Product> findAllProduct(int page);

	// void deleteNews(News news);
	Product findById(Long id);

	Page<Product> findAllProductByTags(List<Ptag> tags);

	Product updateProduct(Product product);

	void deleteProduct(Product product);

	Page<Product> findAllProductByPtags_Tag(String tag, int page);

	Page<Product> findAllProductByNameContaining(String name, int page);
}