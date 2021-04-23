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

	@Autowired
	private RoomBookingRepository roomBookingRP;

	@Transactional
	public void saveRoom(RoomBooking r) throws IncorrectDatesException, ConcurrentBookingException {
		if (!(r.getCheckIn().isAfter(LocalDate.now()) && r.getCheckOut().isAfter(r.getCheckIn()))) {
			throw new IncorrectDatesException();
		}
		Collection<RoomBooking> rooms = roomBookingRP.findByPetId(r.getPet().getId());
		for(RoomBooking room:rooms) {
			if(room.getCheckOut().isAfter(r.getCheckIn())) {
				throw new ConcurrentBookingException();
			}
		}
		roomBookingRP.save(r);
	}

	@Transactional
	public List<RoomBooking> findAll() throws DataAccessException {
		return (List<RoomBooking>) roomBookingRP.findAll();
	}

}
