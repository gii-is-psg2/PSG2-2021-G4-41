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

	@Autowired
	public RoomBookingController(PetService petService) {
		this.petService = petService;
	}

	@ModelAttribute("roomBooking")
	public RoomBooking loadPetWithRoomBooking(@PathVariable("petId") int petId) {
		Pet pet = this.petService.findPetById(petId);
		RoomBooking roomBooking = new RoomBooking();
		pet.addRoomBooking(roomBooking);
		return roomBooking;
	}

	@GetMapping(value = "/owners/*/pets/{petId}/roomBookings/new")
	public String initNewBookingForm(@PathVariable("petId") int petId, Map<String, Object> model) {
		return "pets/createRoomBookingForm";
	}

	@PostMapping(value = "/owners/{ownerId}/pets/{petId}/roomBookings/new")
	public String processNewVisitForm(@Valid RoomBooking roomBooking, BindingResult result) {
		if (result.hasErrors() || !(roomBooking.getCheckIn().isAfter(LocalDate.now())
				&& roomBooking.getCheckOut().isAfter(roomBooking.getCheckIn()))) {
			return "pets/createRoomBookingForm";
		} else {
			this.petService.saveRoomBooking(roomBooking);
		}

		return "redirect:/owners/{ownerId}";
	}
}
