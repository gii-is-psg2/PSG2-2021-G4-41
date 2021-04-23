package org.springframework.samples.petclinic.service;

import java.time.LocalDate;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.RoomBooking;
import org.springframework.samples.petclinic.service.exceptions.ConcurrentBookingException;
import org.springframework.samples.petclinic.service.exceptions.IncorrectDatesException;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class RoomBookingServiceTests {
	
	@Autowired
	protected PetService petService;
	
	@Autowired
	protected RoomBookingService roomBookingService;
	
	@Test
	void shouldInsertRoom() throws IncorrectDatesException, ConcurrentBookingException {
		
		RoomBooking r = new RoomBooking();
		Pet p = petService.findPetById(1);
		
		r.setCheckIn(LocalDate.now().plusDays(2));
		r.setCheckOut(LocalDate.now().plusDays(5));
		r.setPet(p);
		
		roomBookingService.saveRoom(r);
		
		assertTrue(roomBookingService.findAll().size()>0);

	}
	
	@Test
	void shouldNotInsertRoomWithIncorrectDates() throws IncorrectDatesException {
		
		RoomBooking r = new RoomBooking();
		Pet p = petService.findPetById(1);
		
		r.setCheckIn(LocalDate.now().minusDays(2));
		r.setCheckOut(LocalDate.now().plusDays(5));
		r.setPet(p);
		
		assertThrows(IncorrectDatesException.class, () -> roomBookingService.saveRoom(r));

	}

}
