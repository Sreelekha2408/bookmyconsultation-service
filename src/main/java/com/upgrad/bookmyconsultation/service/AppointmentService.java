package com.upgrad.bookmyconsultation.service;

import com.upgrad.bookmyconsultation.entity.Appointment;
import com.upgrad.bookmyconsultation.exception.InvalidInputException;
import com.upgrad.bookmyconsultation.exception.ResourceUnAvailableException;
import com.upgrad.bookmyconsultation.exception.SlotUnavailableException;
import com.upgrad.bookmyconsultation.repository.AppointmentRepository;
import com.upgrad.bookmyconsultation.repository.UserRepository;
import com.upgrad.bookmyconsultation.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

	
	
	//mark it autowired
	//create an instance of AppointmentRepository called appointmentRepository
	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private UserRepository userRepository;

	public String appointment(Appointment appointment) throws SlotUnavailableException, InvalidInputException {
		ValidationUtils.validate(appointment);
		Optional<Appointment> appointmentRes = Optional.ofNullable(appointmentRepository.findByDoctorIdAndTimeSlotAndAppointmentDate(appointment.getDoctorId(),
            appointment.getTimeSlot(), appointment.getAppointmentDate()));
		if(appointmentRes.isPresent()) {
			throw new SlotUnavailableException();
		}
		appointment.setCreatedDate(String.valueOf(LocalDateTime.now()));
		Appointment appointmentSavedRes = appointmentRepository.save(appointment);
		return appointmentSavedRes.getAppointmentId();
	}


	public Appointment getAppointment(String appointmentId) {
		return appointmentRepository.findById(appointmentId)
						.orElseThrow(ResourceUnAvailableException::new);
	}

	public List<Appointment> getAppointmentsForUser(String userId) {
		return appointmentRepository.findByUserId(userId);
	}
}
