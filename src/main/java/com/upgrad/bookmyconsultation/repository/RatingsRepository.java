package com.upgrad.bookmyconsultation.repository;

import com.upgrad.bookmyconsultation.entity.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  RatingsRepository extends CrudRepository<Rating, Long> {
  List<Rating> findByDoctorId(String doctorId);
}

//mark it as repository
//create an interface RatingsRepository that extends CrudRepository
	//create a method findByDoctorId that returns a list of type Rating
	//define method parameter doctorId of type String