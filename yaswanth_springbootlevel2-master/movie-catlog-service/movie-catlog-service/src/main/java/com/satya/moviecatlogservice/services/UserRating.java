package com.satya.moviecatlogservice.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.satya.moviecatlogservice.model.Rating;
import com.satya.moviecatlogservice.model.UserRatings;
@Service
public class UserRating {
	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod ="getFallBackUserRatings")
	public UserRatings getUserRatings(@PathVariable("userId") String userId) {
		return restTemplate.getForObject("http://rating-data-service/rating/user/"+userId, UserRatings.class);
	}
	public UserRatings getFallBackUserRatings(@PathVariable("userId") String userId) {
		UserRatings userRatings=new UserRatings();
		userRatings.setUserId(userId);
		userRatings.setUserRating(Arrays.asList(
				new Rating("0", 0)
				));
		return userRatings;
		
	}
}
