package com.satya.moviecatlogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.satya.moviecatlogservice.model.CatlogItem;
import com.satya.moviecatlogservice.model.Movie;
import com.satya.moviecatlogservice.model.Rating;

@Service
public class MovieInfo {

	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod ="getFallBackCatlogItem")
	public CatlogItem getCatlogItem(Rating rating) {
		Movie movie=restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
		return new CatlogItem(movie.getName(), "test",rating.getRating());
		
	}
	public CatlogItem getFallBackCatlogItem(Rating rating) {
		return new CatlogItem("Movie name not found", "",rating.getRating());
		
	}
}
