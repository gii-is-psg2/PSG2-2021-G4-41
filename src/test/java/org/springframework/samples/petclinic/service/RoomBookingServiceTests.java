package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

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

		roomBookingService.save(r);

		assertTrue(roomBookingService.findAll().size() > 0);

	}

	@Test
	void shouldNotInsertRoomWithIncorrectDates() throws IncorrectDatesException {

		RoomBooking r = new RoomBooking();
		Pet p = petService.findPetById(1);

		r.setCheckIn(LocalDate.now().minusDays(2));
		r.setCheckOut(LocalDate.now().plusDays(5));
		r.setPet(p);

		assertThrows(IncorrectDatesException.class, () -> roomBookingService.save(r));

	}

	@Test
	void shouldNotInsertRoomWithConcurrentDates() throws IncorrectDatesException {

		Pet p = petService.findPetById(1);
		RoomBooking r1 = new RoomBooking();

		r1.setCheckIn(LocalDate.now().plusDays(2));
		r1.setCheckOut(LocalDate.now().plusDays(5));
		r1.setPet(p);
		try {
			roomBookingService.save(r1);
		} catch (ConcurrentBookingException e) {
			e.printStackTrace();
		}

		RoomBooking r2 = new RoomBooking();

		r2.setCheckIn(LocalDate.now().plusDays(3));
		r2.setCheckOut(LocalDate.now().plusDays(6));
		r2.setPet(p);

		assertThrows(ConcurrentBookingException.class, () -> roomBookingService.save(r2));

	}

}
