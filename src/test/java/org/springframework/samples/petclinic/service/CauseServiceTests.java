package org.springframework.samples.petclinic.service;

import static org.junit.Assert.assertTrue;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class CauseServiceTests {
	
	@Autowired
	protected CauseService causeService;
	
	@Test
	void shouldInsertCause() {
		
		Cause c = new Cause();
		c.setId(1);
		c.setName("prueba");
		c.setDescription("prueba");
		c.setOrganization("prueba");
		c.setTarget(1000.);
		c.setOpen(true);

		causeService.saveCause(c);
		
		assertTrue(causeService.findCauseById(1).get().getName().equals("prueba"));
	}

	@Test
	void shouldMakeDonation() {
		
		Cause c = new Cause();
		c.setId(1);
		c.setName("prueba");
		c.setDescription("prueba");
		c.setOrganization("prueba");
		c.setTarget(1000.);
		c.setOpen(true);

		Donation d = new Donation();
		d.setId(1);
		d.setAmount(100.);
		d.setDate(LocalDate.now());
		d.setId(1);
		d.setCause(c);
		
		List<Donation> donations = new ArrayList<>();
		donations.add(d);
		c.setDonations(donations);
		
		causeService.saveCause(c);
		causeService.saveDonation(d);
		assertTrue(causeService.findDonationsByCauseId(1).get(0).getAmount()==100);
		
	}
	
	@Test
	void shouldCloseCause() {
		
		Cause c = new Cause();
		c.setId(1);
		c.setName("prueba");
		c.setDescription("prueba");
		c.setOrganization("prueba");
		c.setTarget(1000.);
		c.setOpen(true);

		Donation d = new Donation();
		d.setId(1);
		d.setAmount(1500.);
		d.setDate(LocalDate.now());
		d.setCause(c);
		
		List<Donation> donations = new ArrayList<>();
		donations.add(d);
		c.setDonations(donations);
		
		causeService.saveCause(c);
		causeService.saveDonation(d);
		System.out.println(causeService.findCauseById(1).get().getOpen());
		assertTrue(causeService.findCauseById(1).get().getOpen().equals(false));
	}
}
