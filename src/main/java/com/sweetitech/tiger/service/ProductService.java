package com.sweetitech.tiger.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sweetitech.tiger.config.Constants;
import com.sweetitech.tiger.model.ecommerce.Product;
import com.sweetitech.tiger.model.ecommerce.Ptag;
import com.sweetitech.tiger.repository.ecommerce.ProductRepository;
import com.sweetitech.tiger.service.interfaces.IProductService;


@Service
@Transactional
public class ProductService implements IProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public Product addProduct(Product product) {
		
		return productRepository.save(product);
	}

	@Override
	public Page<Product> findAllProduct(int page) {
		PageRequest request =
	            new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		
	        return productRepository.findAll(request);
	}

	@Override
	public Product findById(Long id) {
		
		return productRepository.findById(id);
	}

	@Override
	public Page<Product> findAllProductByTags(List<Ptag> ptags) {
		productRepository.findAllProductByPtags(ptags);
		return null;
	}

	@Override
	public Product updateProduct(Product product) {
		
		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(Product product) {
		productRepository.delete(product);
	}

	@Override
	public Page<Product> findAllProductByPtags_Tag(String tag, int page) {
		
		PageRequest request =
	            new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		
		return productRepository.findAllProductByPtags_Tag(tag, request);
	}

	@Override
	public Page<Product> findAllProductByNameContaining(String name, int page) {
		PageRequest request =
	            new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		
		return productRepository.findAllProductByNameContaining(name, request);
	}
	
	
	
	

}
