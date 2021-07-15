package com.divya.civilserviceslondonusers.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.lucene.util.SloppyMath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.divya.civilserviceslondonusers.model.User;

@Service
public class UserWithinRadiusService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserWithinRadiusService.class);

	private final String backendUrl;

	private RestTemplate restTemplate;

	@Autowired
	public UserWithinRadiusService(RestTemplate restTemplate, final @Value("${backend_service_url}") String url) {
		this.restTemplate = restTemplate;
		this.backendUrl = url;
	}

	/**
	 * Method to get all users within a radius of a given longitude and latitude
	 * point.
	 */
	public List<User> getUsersWithinRadius(final double latitudeOfPoint, final double longitudeOfPoint,
			final double radius) {
		final String requestUrl = backendUrl + "users";
		LOGGER.info("Making request to get all users.");
		final ResponseEntity<User[]> responseEntity = restTemplate.exchange(requestUrl, HttpMethod.GET, null,
				User[].class);
		User[] users = responseEntity.getBody();
		List<User> userWithinRadius = users != null ? Arrays.asList(users) : new ArrayList<>();
		LOGGER.info("Filtering results to only users within 50 miles of London.");
		return userWithinRadius.stream().filter(user -> SloppyMath.haversinMeters(latitudeOfPoint, longitudeOfPoint,
				user.getLatitude(), user.getLongitude()) <= radius).collect(Collectors.toList());
	}

}
