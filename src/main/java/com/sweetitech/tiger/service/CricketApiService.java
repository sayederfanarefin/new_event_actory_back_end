package com.sweetitech.tiger.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sweetitech.tiger.config.Constants;
import com.sweetitech.tiger.config.ObjectMapperProvider;
import com.sweetitech.tiger.model.cricketapi.CricketApiLogin;
import com.sweetitech.tiger.model.cricketapi.Data;
import com.sweetitech.tiger.model.cricketapi.Match;
import com.sweetitech.tiger.model.cricketapi.Yo;
import com.sweetitech.tiger.service.interfaces.ICricketApiService;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service("cricketApiService")
public class CricketApiService  implements ICricketApiService{

	String authUrl;
	String matchUrl;
	String recentMatchUrl;
	String season_url;
	
	OkHttpClient client;
	
	
	@Autowired
	HttpSession httpSession;
	
	@Override
	public Data getMatchById(String matchId) {
		
		Request request = new Request.Builder()
				.url(HttpUrl.parse(matchUrl+matchId +"/").newBuilder()
						.addQueryParameter("access_token", getAccessToken()).toString()).get().build();
		
		System.out.println(request.toString());
		
		
		Response response;
		try {
			response = client.newCall(request).execute();
			
			System.out.println(response.code());
			 System.out.println(response.message());
			//ObjectMapper mapper = new ObjectMapper();
			
			ObjectMapperProvider mapper = new ObjectMapperProvider();
			
			return mapper.getContext(ObjectMapper.class).readValue(response.body().bytes(), Yo.class).getData();
					//readValue(response.body().bytes(), Yo.class);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	
	public CricketApiService() {
	      authUrl = Constants.CRICKET_API_BASE + "auth/";
	      matchUrl = Constants.CRICKET_API_BASE + "match/";
	      
	      recentMatchUrl = Constants.CRICKET_API_BASE + "recent_matches/";
	      
	      season_url = Constants.CRICKET_API_BASE + "auth/";
	      client = new OkHttpClient();
	}

	public String getAccessToken() {
		return logIn().getAuth().getAccess_token();
	}
	
	
	public CricketApiLogin logIn() {
		
		RequestBody requestBody = 
				 new FormBody.Builder()
			        .add( Constants.CRICKET_ACCESS_KEY_IDENTIFIER, Constants.CRICKET_ACCESS_KEY)
			        .add(Constants.CRICKET_SECRET_KEY_IDENTIFIER, Constants.CRICKET_SECRET_KEY)
			        .add(Constants.CRICKET_APP_ID_IDENTIFIER, Constants.CRICKET_APP_ID)
			        .add(Constants.CRICKET_DEVICE_ID_IDENTIFIER, Constants.CRICKET_DEVICE_ID)
			        .build();

		Request request = new Request.Builder()
		  
		  .post(requestBody)
		  .addHeader("content-type", "application/x-www-form-urlencoded")
		  .addHeader("cache-control", "no-cache")
		  .url(authUrl)
		  .build();

				try {
					Response response = client.newCall(request).execute();
					
					//System.out.println(response.body().string());;
					ObjectMapper mapper = new ObjectMapper();
					
					
					CricketApiLogin cricketApiLogin = new ObjectMapper().readValue(response.body().bytes(), CricketApiLogin.class);
					
					
					System.out.println("==========================="+ cricketApiLogin.getAuth().getAccess_token());
					
					httpSession.setAttribute(Constants.CRICKET_API_SESSION_ACCESS_TOKEN_IDENTIFIER, cricketApiLogin.getAuth().getAccess_token());
					httpSession.setAttribute(Constants.CRICKET_SESSION_CACHE_KEY_IDENTIFIER, cricketApiLogin.getCache_key());
					httpSession.setAttribute(Constants.CRICKET_API_SESSION_ACCESS_TOKEN_EXPIRE_IDENTIFIER, cricketApiLogin.getAuth().getExpires());
					
					
		            return cricketApiLogin;
		            
				} catch (IOException e) {
					System.out.println(e.getMessage());
				
					httpSession.setAttribute(Constants.CRICKET_API_SESSION_ACCESS_TOKEN_IDENTIFIER, null);
					httpSession.setAttribute(Constants.CRICKET_SESSION_CACHE_KEY_IDENTIFIER, null);
					httpSession.setAttribute(Constants.CRICKET_API_SESSION_ACCESS_TOKEN_EXPIRE_IDENTIFIER, null);
					
					return null;
				}

	}

	@Override
	public List<Match> getResentMatches() {
		// TODO Auto-generated method stub
//		Request request = new Request.Builder()
//				.url(HttpUrl.parse(recentMatchUrl).newBuilder()
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
//			return mapper.getContext(ObjectMapper.class).readValue(response.body().bytes(), Yo.class).getData();
//					//readValue(response.body().bytes(), Yo.class);
//			
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
		
		return null;
	}

}
