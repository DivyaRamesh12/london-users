package com.divya.civilserviceslondonusers.service;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.divya.civilserviceslondonusers.model.User;

/**
 * Service to return all the user from a city.
 */
@Service
public class UsersFromCityService {
	/**
	 * Base url for back-end call.
	 */
	private final String backendUrl;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserWithinRadiusService.class);

	/**
	 * Define restTemplate.
	 */
	private RestTemplate restTemplate;

	/**
	 * restTemplate used to make the back-end request.
	 */
	@Autowired
	public UsersFromCityService(RestTemplate restTemplate, final @Value("${backend_service_url}") String url) {
		this.restTemplate = restTemplate;
		this.backendUrl = url;
	}

	/**
	 * Get List of user from a given city.
	 */
	public List<User> getUsersFromCity(final String city) {
		final String requestUrl = backendUrl + "city/" + city + "/users";
		LOGGER.info("Request all the users listed as from London." + requestUrl);
		final ResponseEntity<User[]> responseEntity = restTemplate.exchange(requestUrl, HttpMethod.GET, null,
				User[].class);
		final User[] users = responseEntity.getBody();
		return users != null ? Arrays.asList(users) : new ArrayList<>();

	}
}
