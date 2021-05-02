package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.AdoptionRequest;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.AdoptionService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = AdoptionController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
class AdoptionControllerTests {
	private static final int TEST_REQUEST_ID = 1;
	private static final int TEST_OWNER_ID = 1;

	@MockBean
	private AdoptionService adoptionService;
	@MockBean
	private OwnerService ownerService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {

		AdoptionRequest mark = new AdoptionRequest();
		mark.setId(1);
		given(this.adoptionService.listAdoptionRequests()).willReturn(Lists.newArrayList(mark));
		given(this.ownerService.findOwnerById(TEST_OWNER_ID)).willReturn(new Owner());

	}

	@WithMockUser(value = "spring")
	@Test
	void testInitNewRequestFormSuccess() throws Exception {
		mockMvc.perform(get("/adoptions/requests/new")).andExpect(status().isOk()).andExpect(view().name("exception"));

	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessNewRequestFormSuccess() throws Exception {
		mockMvc.perform(post("/adoptions/requests/new").with(csrf()).param("type", "dog").param("city", "McFarland"))
				.andExpect(status().isOk()).andExpect(view().name("adoptions/createAdoptionRequest"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testInitNewApplicationFormSuccess() throws Exception {
		mockMvc.perform(get("/adoptions/requests/{requestId}/applications", TEST_REQUEST_ID)).andExpect(status().isOk())
				.andExpect(view().name("adoptions/listApplications"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessApplicationFormSuccess() throws Exception {
		mockMvc.perform(post("/adoptions/requests/{requestId}/applications/new", TEST_REQUEST_ID).with(csrf())
				.param("type", "cat").param("city", "Madison")).andExpect(status().isOk())
				.andExpect(view().name("adoptions/createAdoptionApplication"));
	}

}