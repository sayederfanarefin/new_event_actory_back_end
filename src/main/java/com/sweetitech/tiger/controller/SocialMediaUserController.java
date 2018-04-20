package com.sweetitech.tiger.controller;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.sweetitech.tiger.dto.UserDto;
import org.sweetitech.tiger.dto.UserProfileDto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sweetitech.tiger.config.ObjectMapperProvider;
import com.sweetitech.tiger.exception.UserAlreadyExistException;
import com.sweetitech.tiger.model.Image;
import com.sweetitech.tiger.model.User;
import com.sweetitech.tiger.model.facebook.FacebookProfileCustom;
import com.sweetitech.tiger.service.interfaces.IImageService;
import com.sweetitech.tiger.service.interfaces.IUserService;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController
@RequestMapping("/sso")
public class SocialMediaUserController {
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IImageService imageService;
	
	
	OkHttpClient client;
	@Autowired
	HttpSession httpSession;

	@RequestMapping(value = "/facebook/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> facebookLogin(@Valid Principal principal,
			@RequestParam(value = "accessToken", required = true) String accessToken,
			@RequestParam(value = "userId", required = true) String userId) {
		client = new OkHttpClient();
		Request request = new Request.Builder()
				.url(HttpUrl.parse("https://graph.facebook.com/v2.11/me").newBuilder()
						.addQueryParameter("access_token", accessToken)
						.addQueryParameter("fields", "id,name,email,picture,gender,first_name,last_name").toString())
				.get().build();
		try {
			Response response = client.newCall(request).execute();
			ObjectMapperProvider mapper = new ObjectMapperProvider();
			FacebookProfileCustom facebookProfileCustom = mapper.getContext(ObjectMapper.class)
					.readValue(response.body().bytes(), FacebookProfileCustom.class);

			if (userId.equals(facebookProfileCustom.getId())) {
				User u = userService.findUserByEmail(facebookProfileCustom.getEmail());
				OAuth2AccessToken access_token = generateOAuth2AccessToken(facebookProfileCustom.getEmail(),
						facebookProfileCustom.getId());
				return new ResponseEntity<>(access_token, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Wrong user id and access_token combination", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/facebook/register", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> facebookRegister(@Valid Principal principal,
			@RequestParam(value = "accessToken", required = true) String accessToken,
			@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "phoneNumber", required = true) String phoneNumber,
			@RequestParam(value = "userName", required = true) String userName) {
		client = new OkHttpClient();
		Request request = new Request.Builder()
				.url(HttpUrl.parse("https://graph.facebook.com/v2.11/me").newBuilder()
						.addQueryParameter("access_token", accessToken)
						.addQueryParameter("fields", "id,name,email,picture,gender,first_name,last_name").toString())
				.get().build();
		try {
			Response response = client.newCall(request).execute();
			ObjectMapperProvider mapper = new ObjectMapperProvider();
			FacebookProfileCustom facebookProfileCustom = mapper.getContext(ObjectMapper.class)
					.readValue(response.body().bytes(), FacebookProfileCustom.class);

			if (userId.equals(facebookProfileCustom.getId())) {
				UserDto accountDto = new UserDto(facebookProfileCustom.getFirst_name(),
						facebookProfileCustom.getLast_name(), facebookProfileCustom.getId(), // getSaltString(),
						facebookProfileCustom.getEmail(), userName, phoneNumber, 1);
				try {

//					System.out.println(
//							facebookProfileCustom.getFirst_name() + " " + facebookProfileCustom.getLast_name());

					UserProfileDto userDto2 = userService.findUserProfileDtoByEmail(facebookProfileCustom.getEmail());

					if (userDto2 == null) {

						final User registered = userService.registerNewUserAccount(accountDto);

						if (registered == null) {

							return new ResponseEntity<>(
									"Some thing went wrong. Please check your parameters and try again.",
									HttpStatus.BAD_REQUEST);
						} else {
							
							Image i = imageService.addImage(new Image(facebookProfileCustom.getPicture().getData().getUrl()));
							
							registered.setProfilePicture(i);

							OAuth2AccessToken access_token = generateOAuth2AccessToken(accountDto.getEmail(),
									accountDto.getPassword());

							return new ResponseEntity<>(access_token, HttpStatus.OK);// HttpStatus.ACCEPTED;
						}
					} else {
						// user exists, return token

						User u = userService.findUserByEmail(userDto2.getEmail());
						OAuth2AccessToken access_token = generateOAuth2AccessToken(u.getEmail(),
								facebookProfileCustom.getId());
						return new ResponseEntity<>(access_token, HttpStatus.OK);
					}
				} catch (UserAlreadyExistException uae) {
					return new ResponseEntity<>(uae.getMessage(), HttpStatus.CONFLICT);
				} catch (Exception a) {
					a.printStackTrace();
					return new ResponseEntity<>("Some thing went wrong. Please try again.3", HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<>("Wrong user id and access_token combination", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Some thing went wrong.  Please try again. 1", HttpStatus.BAD_REQUEST);
		}

	}

	// protected String getSaltString() {
	// String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	// StringBuilder salt = new StringBuilder();
	// Random rnd = new Random();
	// while (salt.length() < 256) { // length of the random string.
	// int index = (int) (rnd.nextFloat() * SALTCHARS.length());
	// salt.append(SALTCHARS.charAt(index));
	// }
	// String saltStr = salt.toString();
	// return saltStr;
	//
	// }
	//
	//
	//
	@Autowired
	private AuthorizationServerEndpointsConfiguration configuration;

	public OAuth2AccessToken generateOAuth2AccessToken(String email, String Password) {

		List<String> scopes = new ArrayList<String>();
		scopes.add("trust");

		Map<String, String> requestParameters = new HashMap<String, String>();

		requestParameters.put("username", email);
		requestParameters.put("password", Password);
		requestParameters.put("grant_type", "password");
		requestParameters.put("client_id", "android-client");
		requestParameters.put("client_secret", "android-secret");

		Map<String, Serializable> extensionProperties = new HashMap<String, Serializable>();

		boolean approved = true;
		Set<String> responseTypes = new HashSet<String>();
		responseTypes.add("code");

		// Authorities
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		// OAuth2Request oauth2Request = new OAuth2Request(requestParameters,
		// "android-client", authorities, approved, new HashSet<String>(scopes), new
		// HashSet<String>(Arrays.asList("oauth2-resource")), null, responseTypes,
		// extensionProperties);

		OAuth2Request oauth2Request = new OAuth2Request(requestParameters, "android-client", authorities, approved,
				new HashSet<String>(scopes), new HashSet<String>(Arrays.asList("oauth2-resource")), null, null, null);// responseTypes,
																														// extensionProperties)

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, "N/A",
				authorities);

		OAuth2Authentication auth = new OAuth2Authentication(oauth2Request, authenticationToken);

		AuthorizationServerTokenServices tokenService = configuration.getEndpointsConfigurer().getTokenServices();

		OAuth2AccessToken token = tokenService.createAccessToken(auth);

		// new Manual
		// token.getRefreshToken();
		// token.getExpiresIn();
		// token.getAdditionalInformation();

		return token;
	}
}
