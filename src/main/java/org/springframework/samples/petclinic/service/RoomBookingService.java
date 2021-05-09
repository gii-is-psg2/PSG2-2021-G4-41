package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.RoomBooking;
import org.springframework.samples.petclinic.repository.RoomBookingRepository;
import org.springframework.samples.petclinic.service.exceptions.ConcurrentBookingException;
import org.springframework.samples.petclinic.service.exceptions.IncorrectDatesException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoomBookingService {

	private RoomBookingRepository roomBookingRP;

	@Autowired
	public RoomBookingService(RoomBookingRepository roomBookingRP) {
		this.roomBookingRP = roomBookingRP;
	}

	@Transactional
	public void save(RoomBooking r) throws IncorrectDatesException, ConcurrentBookingException {
		Collection<RoomBooking> bookings = roomBookingRP.findByPetId(r.getPet().getId());
		if (!(r.getCheckIn().isAfter(LocalDate.now()) && r.getCheckOut().isAfter(r.getCheckIn()))) {
			throw new IncorrectDatesException();
		} else if (bookings.stream().anyMatch(
				room -> !(room.getCheckOut().isBefore(r.getCheckIn()) || room.getCheckIn().isAfter(r.getCheckOut())))) {
			throw new ConcurrentBookingException();
		} else
			roomBookingRP.save(r);

	}

	@Transactional
	public List<RoomBooking> findAll() throws DataAccessException {
		return roomBookingRP.findAll();
	}

}
