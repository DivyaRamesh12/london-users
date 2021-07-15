package com.divya.civilserviceslondonusers.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.divya.civilserviceslondonusers.model.User;
import com.divya.civilserviceslondonusers.service.UserWithinRadiusService;
import com.divya.civilserviceslondonusers.service.UsersInOrAroundLondonService;

/**
 * Controller defining the REST endpoint of the application.
 */
@RestController
public class LondonUsersController {

	/**
	 * Logger for this service.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserWithinRadiusService.class);

	/**
	 * UserInOrAroundCityService init.
	 */
	private final UsersInOrAroundLondonService usersInOrAroundLondonService;

	/**
	 * Constructor for controller auto-wiring service. usersInOrAroundLondonService
	 * - Service to get in and around city
	 */
	@Autowired
	public LondonUsersController(UsersInOrAroundLondonService usersInOrAroundLondonService) {
		this.usersInOrAroundLondonService = usersInOrAroundLondonService;
	}

	@GetMapping(value = "v1/london-users", produces = { "applicatio/json" })
	public ResponseEntity<List<User>> getLondonUsers() {
		LOGGER.info("Received request, call service to get corresponding users");
		List<User> londonUsers = usersInOrAroundLondonService.getUsersInOrAroundLondonService();
		LOGGER.info("Received request, call service to get corresponding users ha ha");
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(londonUsers);
	}
}
