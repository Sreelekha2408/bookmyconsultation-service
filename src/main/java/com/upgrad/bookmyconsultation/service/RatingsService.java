package com.upgrad.bookmyconsultation.service;

import com.upgrad.bookmyconsultation.entity.Doctor;
import com.upgrad.bookmyconsultation.entity.Rating;
import com.upgrad.bookmyconsultation.repository.DoctorRepository;
import com.upgrad.bookmyconsultation.repository.RatingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class RatingsService {

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private RatingsRepository ratingsRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	
	//create a method name submitRatings with void return type and parameter of type Rating
		//set a UUID for the rating
		//save the rating to the database
		//get the doctor id from the rating object
		//find that specific doctor with the using doctor id
		//modify the average rating for that specific doctor by including the new rating
		//save the doctor object to the database
	public void submitRatings(Rating rating) {
		rating.setId(UUID.randomUUID().toString());
		Rating saveRes = ratingsRepository.save(rating);
		String doctorId = saveRes.getDoctorId();
		Doctor doctor = doctorRepository.findById(doctorId).get();
		Double newAvgRating = calculateAvgRating(doctor);
		doctor.setRating(newAvgRating);
		doctorRepository.save(doctor);
	}

	private double calculateAvgRating(Doctor doctor) {
		List<Rating> ratings = ratingsRepository.findByDoctorId(doctor.getId());
		double totalRating = ratings.stream().mapToDouble(Rating::getRating).sum();
		return totalRating / ratings.size();
	}

}
