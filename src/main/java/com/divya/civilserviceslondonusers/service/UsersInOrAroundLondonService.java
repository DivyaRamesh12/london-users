package com.divya.civilserviceslondonusers.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divya.civilserviceslondonusers.model.User;

@Service
public class UsersInOrAroundLondonService {
	final private String LONDON = "London";
	final private double RADIUS = 80467.2;
	final private double LONDON_LATITUDE = 51.507222;
	final private double LONDON_LONGITUDE = -0.1275;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserWithinRadiusService.class);
	private final UsersFromCityService usersFromCityService;
	private final UserWithinRadiusService userWithinRadiusService;

	@Autowired
	public UsersInOrAroundLondonService(UsersFromCityService usersFromCityService,
			UserWithinRadiusService userWithinRadiusService) {
		this.usersFromCityService = usersFromCityService;
		this.userWithinRadiusService = userWithinRadiusService;
	}

	/**
	 * Get combined list of all the user in London or within 50 miles.
	 */
	public List<User> getUsersInOrAroundLondonService() {
		List<User> londonUsers = usersFromCityService.getUsersFromCity(LONDON);
		List<User> usersWithinFiftyMilesOfLondon = userWithinRadiusService.getUsersWithinRadius(LONDON_LATITUDE,
				LONDON_LONGITUDE, RADIUS);
		LOGGER.info("Combine results from both queries.");
		return Stream.of(londonUsers, usersWithinFiftyMilesOfLondon).flatMap(Collection::stream).distinct()
				.collect(Collectors.toList());
	}

}
