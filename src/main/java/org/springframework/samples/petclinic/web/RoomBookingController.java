package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.RoomBooking;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

//	@PostMapping(value = "/owners/{ownerId}/pets/{petId}/roomBookings/new")
//	public String processNewVisitForm(@Valid RoomBooking r, BindingResult result) {
//		if (result.hasErrors() || !(r.getCheckOut().isAfter(r.getCheckIn()))) {
//			result.rejectValue("checkOut", "error.IncorrectCheckOut", "La fecha de fin debe ser posterior a la fecha de inicio");
//			return "pets/createRoomBookingForm";
//		} else {
//			this.petService.saveRoomBooking(r);
//		}
//
//		return "redirect:/owners/{ownerId}";
//	}
	
	@PostMapping(value = "/owners/{ownerId}/pets/{petId}/roomBookings/new")
	public String processNewVisitForm(@Valid RoomBooking r, BindingResult result) {
		
		boolean c1 = r.getCheckIn().isBefore(LocalDate.now());
		boolean c2 = r.getCheckOut().isBefore(r.getCheckIn());
		
		if (c1 && c2) {
			result.rejectValue("checkIn", "error.IncorrectCheckIn", "Check in's date must be after today's date");
			result.rejectValue("checkOut", "error.IncorrectCheckOut", "Check out's date must be after check out's date");
			return "pets/createRoomBookingForm";
		} else if(!c1 && c2) {
			result.rejectValue("checkOut", "error.IncorrectCheckOut", "Check out's date must be after check out's date");
			return "pets/createRoomBookingForm";
		} else if(c1 && !c2) {
			result.rejectValue("checkIn", "error.IncorrectCheckIn", "Check in's date must be after today's date");
			return "pets/createRoomBookingForm";
		} else {
			this.petService.saveRoomBooking(r);
		}

		return "redirect:/owners/{ownerId}";
	}
}
