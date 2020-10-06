package com.satya.moviecatlogservice.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.satya.moviecatlogservice.model.CatlogItem;
import com.satya.moviecatlogservice.model.Movie;
import com.satya.moviecatlogservice.model.Rating;
import com.satya.moviecatlogservice.model.UserRatings;
import com.satya.moviecatlogservice.services.MovieInfo;
import com.satya.moviecatlogservice.services.UserRating;
@RestController
@RequestMapping("/catlog")
public class MovieCatlogResources {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private WebClient.Builder webbuilder;
	
	@Autowired
	 MovieInfo movieInfo;
	
	@Autowired
	 UserRating userRatings;
	
	
	
	
	
	
	@RequestMapping("/{userId}")
	//@HystrixCommand(fallbackMethod ="getFallBackCatlog")
	public List<CatlogItem> getCatlog(@PathVariable("userId") String userId) {
		UserRatings ratings=userRatings.getUserRatings(userId);
		return ratings.getUserRating().stream().map(rating -> {
	return movieInfo.getCatlogItem(rating);
		})
    
			
		
		.collect(Collectors.toList());
				
				
	}

	
}
