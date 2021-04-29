package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.RoomBooking;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.RoomBookingService;
import org.springframework.samples.petclinic.service.exceptions.IncorrectDatesException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RoomBookingController {

	private final PetService petService;
    private final AdoptionService adoptionService;
    private final OwnerService ownerService;

	@Autowired
	public RoomBookingController(PetService petService,  AdoptionService adoptionService, OwnerService ownerService) {
		this.petService = petService;
        this.adoptionService = adoptionService;
        this.ownerService = ownerService;
	}

    @GetMapping(value = "/adoptions/requests")
	public String listRequests(Map<String, Object> model) {

        List<AdoptionRequest> requests = adoptionService.listAdoptionRequests();
        List<AdoptionRequest> myRequests = adoptionService.listMyAdoptionRequests();
        model.setAttribute("requests", requests);
        model.setAttribute("myRequests", myRequests);
		return "adoptions/listRequests";
	}

	@GetMapping(value = "/adoptions/requests/new")
	public String initNewRequestForm(Map<String, Object> model) {
        AdoptionRequest newRequest = new AdoptionRequest();
        Owner owner = ownerService.findById();
        newRequest.setOwner(owner);
        model.setAttribute("pets", owner.pets);
        model.setAttribute("newRequest", newRequest);
		return "adoptions/createAdoptionRequest";
	}

    @PostMapping(value = "/adoptions/requests/{requestId}/applications/new")
	public String processNewRequestForm(@Valid RoomBooking r, BindingResult result) {
        
        // TODO
		return "redirect:/adoptions/requests";
	}

    @GetMapping(value = "/adoptions/requests/{requestId}/applications")
	public String listApplications(Map<String, Object> model, @PathVariable("requestId" requestId, @PathVariable("applicationId}") applicationId) ) {
        List<AdoptionApplication> applications = adoptionService.listAdoptionApplicationsByRequestId(requestId);
        model.setAttribute("applications", applications);
		return "adoptions/listApplications";
	}

    @GetMapping(value = "/adoptions/requests/{requestId}/applications/new")
	public String initNewApplicationForm(Map<String, Object> model, @PathVariable("requestId" requestId) {
        AdoptionRequest request = adoptionService.findRequestById(requestId);
        AdoptionApplication newApplication = new AdoptionApplication();
        newApplication.setRequest(request)
        model.setAttribute("newApplication", newApplication)
		return "adoptions/createAdoptionApplication";
	}

    @PostMapping(value = "/adoptions/requests/{requestId}/applications/new")
	public String processNewApplicationForm(@Valid AdoptionApplication application, BindingResult result) {
        
        // TODO
		return "redirect:/adoptions/requests";
	}

    @PostMapping(value = "/adoptions/requests/{requestId}/applications/{applicationId}/confirm")
	public String processNewApplicationForm(@Valid AdoptionApplication application, BindingResult result) {
        
        // TODO
		return "redirect:/adoptions/requests";
	}

    @PostMapping(value ="/adop")
}
