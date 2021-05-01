package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.AdoptionApplication;
import org.springframework.samples.petclinic.model.AdoptionRequest;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.RoomBooking;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.AdoptionService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdoptionController {

    private final AdoptionService adoptionService;
    private final OwnerService ownerService;
    private final UserService userService;

    @Autowired
    public AdoptionController(AdoptionService adoptionService, OwnerService ownerService, UserService userService) {
        this.adoptionService = adoptionService;
        this.ownerService = ownerService;
        this.userService = userService;
    }

    private Owner loggedOwner() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Owner owner = ownerService.findOwnerByUsername(username);
        return owner;
    }

    @GetMapping(value = "/adoptions/requests")
    public String listRequests(Map<String, Object> model) {
        Owner owner = loggedOwner();
        List<AdoptionRequest> requests = adoptionService.listAdoptionRequests().stream()
                .filter(r -> r.getOwner() != owner && !r.isClosed()).collect(Collectors.toList());
        List<AdoptionRequest> myRequests = owner.getId() != null
                ? (List<AdoptionRequest>) adoptionService.listMyAdoptionRequests(owner.getId()).stream()
                        .filter(r -> !r.isClosed()).collect(Collectors.toList())
                : new ArrayList<>();
        model.put("requests", requests);
        model.put("myRequests", myRequests);
        return "adoptions/listRequests";
    }

    @GetMapping(value = "/adoptions/requests/new")
    public String initNewRequestForm(Map<String, Object> model) {
        model.put("pets", loggedOwner().getPets());
        return "adoptions/createAdoptionRequest";
    }

    @PostMapping(value = "/adoptions/requests/new")
    public String processNewRequestForm(@Valid AdoptionRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/adoptions/requests";
        } else
            adoptionService.saveAdoptionRequest(request);
        return "redirect:/adoptions/requests";
    }

    @GetMapping(value = "/adoptions/requests/{requestId}/applications")
    public String listApplications(Map<String, Object> model, @PathVariable("requestId") Integer requestId) {
        List<AdoptionApplication> applications = (List<AdoptionApplication>) adoptionService
                .listAdoptionApplicationsByRequestId(requestId);
        AdoptionRequest request = adoptionService.findRequestById(requestId);
        model.put("request", request);
        model.put("applications", applications);
        return "adoptions/listApplications";
    }

    @GetMapping(value = "/adoptions/requests/{requestId}/applications/new")
    public String initNewApplicationForm(Map<String, Object> model, @PathVariable("requestId") Integer requestId) {
        AdoptionRequest request = adoptionService.findRequestById(requestId);
        AdoptionApplication newApplication = new AdoptionApplication();
        newApplication.setRequest(request);
        model.put("newApplication", newApplication);
        return "adoptions/createAdoptionApplication";
    }

    @PostMapping(value = "/adoptions/requests/{requestId}/applications/new")
    public String processNewApplicationForm(@PathVariable("requestId") Integer requestId,
            @Valid AdoptionApplication application, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/adoptions/requests";
        } else {
            AdoptionRequest request = adoptionService.findRequestById(requestId);
            AdoptionApplication newApplication = new AdoptionApplication();
            newApplication.setFutureOwner(loggedOwner());
            newApplication.setApproved(false);
            newApplication.setDescription(application.getDescription());
            newApplication.setRequest(request);
            adoptionService.saveAdoptionApplication(newApplication);
        }
        return "redirect:/adoptions/requests";
    }

    @GetMapping(value = "/adoptions/requests/{requestId}/applications/{applicationId}/confirm")
    public String confirmApplication(@PathVariable("applicationId") Integer applicationId) {
        AdoptionApplication application = adoptionService.findApplicationById(applicationId);
        if (application.getRequest().getPet().getOwner() == this.loggedOwner()) {
            try {
                adoptionService.confirmApplication(application);
                return "redirect:/adoptions/requests";
            } catch (DataAccessException | DuplicatedPetNameException e) {
                return "redirect:/oups";
            }
        }
        return "redirect:/oups";
    }

}
