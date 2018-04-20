package com.sweetitech.tiger.service.interfaces;

import org.springframework.data.domain.Page;

import com.sweetitech.tiger.model.Banner;
import com.sweetitech.tiger.model.Video;



public interface IBannerService {

	Banner addBanner(Banner banner);
	Page<Banner> findAllBanner(int page);
	//void deleteNews(News news);
	Banner findById(Long id);
	
	Page<Banner> findAllBannerByBannerSize(long bannerSizeId, int page);
	
	Banner updateBanner(Banner banner);
	
	void deleteBanner(Banner banner);
}
