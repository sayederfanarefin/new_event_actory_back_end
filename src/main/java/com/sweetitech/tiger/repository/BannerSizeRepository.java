package com.sweetitech.tiger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sweetitech.tiger.model.BannerSize;

@Repository("bannerSizeRepository")
public interface BannerSizeRepository extends JpaRepository<BannerSize, Integer>{
	
	 public List<BannerSize> findAllByOrderByIdAsc();
	 
	 public BannerSize findById(Long id);
	 
	 public BannerSize save(BannerSize bannerSize);
	 
	 
	
}

