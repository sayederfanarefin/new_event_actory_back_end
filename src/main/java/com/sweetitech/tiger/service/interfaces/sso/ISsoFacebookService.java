package com.sweetitech.tiger.service.interfaces.sso;

import org.springframework.data.domain.Page;

import com.sweetitech.tiger.model.Banner;



public interface ISsoFacebookService {
	public void checkFromFbServer(String accessToken, String userId) ;
}
