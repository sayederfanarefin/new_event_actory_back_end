package com.sweetitech.tiger.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sweetitech.tiger.config.Constants;
import com.sweetitech.tiger.model.Banner;
import com.sweetitech.tiger.repository.BannerRepository;
import com.sweetitech.tiger.service.interfaces.IBannerService;


@Service
@Transactional
public class BannerService implements IBannerService {

	@Autowired
	BannerRepository bannerRepository;
	
	
	
	@Override
	public Page<Banner> findAllBanner(int page) {
		
		PageRequest request =
	            new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		
	        return bannerRepository.findAll(request);
	}
	
	@Override
	public Banner addBanner(Banner banner) {
		
		return bannerRepository.save(banner);
	}
	
	@Override
	public Banner findById(Long id) {
		
		return bannerRepository.findById(id);
	}




	@Override
	public Page<Banner> findAllBannerByBannerSize(long bannerSizeId, int page) {
		Pageable pageable =
	            new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		
		
	        return bannerRepository.findAllByBannerSize_Id(bannerSizeId, pageable);
	}




	@Override
	public Banner updateBanner(Banner banner) {
		
		return bannerRepository.save(banner);
	}




	@Override
	public void deleteBanner(Banner banner) {
		bannerRepository.delete(banner);
	}
	
	

}
