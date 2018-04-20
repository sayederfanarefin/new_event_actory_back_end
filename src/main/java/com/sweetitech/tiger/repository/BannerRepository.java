package com.sweetitech.tiger.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sweetitech.tiger.model.Banner;

@Repository("bannerRepository")
public interface BannerRepository extends JpaRepository<Banner, Integer>{
	
	 public List<Banner> findAllByOrderByIdAsc();
	 
	 public Banner findById(Long id);
	 
	 public Banner save(Banner banner);
	
	 public Page<Banner> findAllByBannerSize_Id (long id, Pageable pageable);
}

