package com.upgrad.bookmyconsultation.controller;

import com.upgrad.bookmyconsultation.entity.Appointment;
import com.upgrad.bookmyconsultation.exception.InvalidInputException;
import com.upgrad.bookmyconsultation.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;


	//Method to book an appointment
	@PostMapping
	public ResponseEntity<String> bookAppointment(@RequestBody Appointment appointment) throws InvalidInputException {
		return new ResponseEntity<>(appointmentService.appointment(appointment), HttpStatus.CREATED);
	}

	// Method to get appointment details
	@GetMapping("/{appointmentId}")
	public ResponseEntity getAppointments(@PathVariable String appointmentId) {
		return new ResponseEntity(appointmentService.getAppointment(appointmentId), HttpStatus.OK);
	}
	

}