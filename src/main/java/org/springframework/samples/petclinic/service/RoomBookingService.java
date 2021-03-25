package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.RoomBooking;
import org.springframework.samples.petclinic.repository.RoomBookingRepository;
import org.springframework.samples.petclinic.service.exceptions.IncorrectDatesException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoomBookingService {

	@Autowired
	private RoomBookingRepository roomBookingRP;

	@Transactional
	public void saveRoom(RoomBooking r) throws IncorrectDatesException {
		if (!(r.getCheckIn().isAfter(LocalDate.now()) && r.getCheckOut().isAfter(r.getCheckIn()))) {
			throw new IncorrectDatesException();
		}
		roomBookingRP.save(r);
	}

	@Transactional
	public List<RoomBooking> findAll() throws DataAccessException {
		return (List<RoomBooking>) roomBookingRP.findAll();
	}

}
