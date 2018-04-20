package com.sweetitech.tiger.service.sso;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.sweetitech.tiger.dto.UserDto;
import org.sweetitech.tiger.dto.UserProfileDto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sweetitech.tiger.config.ObjectMapperProvider;
import com.sweetitech.tiger.exception.UserAlreadyExistException;
import com.sweetitech.tiger.model.PasswordResetToken;
import com.sweetitech.tiger.model.Role;
import com.sweetitech.tiger.model.User;
import com.sweetitech.tiger.model.cricketapi.Data;
import com.sweetitech.tiger.model.cricketapi.Yo;
import com.sweetitech.tiger.repository.PasswordResetTokenRepository;
import com.sweetitech.tiger.repository.RoleRepository;
import com.sweetitech.tiger.repository.UsersRepository;
import com.sweetitech.tiger.service.interfaces.sso.ISsoFacebookService;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
@Transactional
public class SsoFacebookService implements ISsoFacebookService {

	@Autowired
	private UsersRepository repository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	String GraphURL = "https://graph.facebook.com/v2.11";

	OkHttpClient client;

	@Autowired
	HttpSession httpSession;

	@Override
	public void checkFromFbServer(String accessToken, String userId) {
		
//		Request request = new Request.Builder()
//				.url(HttpUrl.parse(GraphURL).newBuilder()
//						.addQueryParameter("access_token", getAccessToken()).toString()).get().build();
//		
//		System.out.println(request.toString());
//		
//		
//		Response response;
//		try {
//			response = client.newCall(request).execute();
//			
//			System.out.println(response.code());
//			 System.out.println(response.message());
//			//ObjectMapper mapper = new ObjectMapper();
//			
//			ObjectMapperProvider mapper = new ObjectMapperProvider();
//			
//			//return mapper.getContext(ObjectMapper.class).readValue(response.body().bytes(), Yo.class).getData();
//					//readValue(response.body().bytes(), Yo.class);
//			
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			//return null;
//		}

	}
	
}