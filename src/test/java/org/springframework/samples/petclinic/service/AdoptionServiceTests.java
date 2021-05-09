package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.AdoptionApplication;
import org.springframework.samples.petclinic.model.AdoptionRequest;
import org.springframework.samples.petclinic.util.EntityUtils;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class AdoptionServiceTests {

	@Autowired
	protected AdoptionService adoptionService;

	@Test
	void shouldAdoptionRequest() {
		Collection<AdoptionRequest> AdoptionRequest = this.adoptionService.listAdoptionRequests();

		AdoptionRequest adoption = EntityUtils.getById(AdoptionRequest, AdoptionRequest.class, 1);
		assertEquals(adoption.getPet(), adoption.getPet());
		assertEquals(adoption.getOwner(), adoption.getOwner());
		assertEquals(1, adoption.getId());
	}

	@Test
	void shouldFindApplicationById() {
		AdoptionApplication adoptionId = this.adoptionService.findApplicationById(1);
		assertEquals(1, adoptionId.getId());
	}

}
