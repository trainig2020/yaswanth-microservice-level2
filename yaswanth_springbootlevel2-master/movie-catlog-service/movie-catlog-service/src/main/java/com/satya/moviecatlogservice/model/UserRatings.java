package com.satya.moviecatlogservice.model;

import java.util.List;

public class UserRatings {

	private List<Rating> userRating;
	private String userId;

	

	public List<Rating> getUserRating() {
		return userRating;
	}

	public void setUserRating(List<Rating> userRating) {
		this.userRating = userRating;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

	public UserRatings(List<Rating> userRating, String userId) {
		super();
		this.userRating = userRating;
		this.userId = userId;
	}

	public UserRatings() {
		super();
	}
	
}
