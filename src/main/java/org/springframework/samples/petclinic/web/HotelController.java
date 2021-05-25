package org.springframework.samples.petclinic.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.exceptions.ForbiddenException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HotelController {

    private final OwnerService ownerService;
    private static final String HOTEL_VIEW = "hotel/hotel";

    @Autowired
    public HotelController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping(value = "/hotel")
    public String hotelPreview(Map<String, Object> model) throws ForbiddenException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Owner owner = ownerService.findOwnerByUsername(username);
        if (owner == null)
            throw new ForbiddenException();

        model.put("owner", owner);
        return HOTEL_VIEW;
    }
}
