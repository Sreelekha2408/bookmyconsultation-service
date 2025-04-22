package com.upgrad.bookmyconsultation.controller;

import com.upgrad.bookmyconsultation.entity.Rating;
import com.upgrad.bookmyconsultation.service.RatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class RatingsController {

	@Autowired
	private RatingsService ratingsService;


	//Method to submit rating of doctor's appointment
	@PostMapping("/ratings")
	@ResponseBody
	public ResponseEntity submitRatings(@RequestBody Rating rating) {
		ratingsService.submitRatings(rating);
		return ResponseEntity.ok("success");
	}

}
