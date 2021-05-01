package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.RoomBooking;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.RoomBookingService;
import org.springframework.samples.petclinic.service.exceptions.ConcurrentBookingException;
import org.springframework.samples.petclinic.service.exceptions.IncorrectDatesException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RoomBookingController {

	private final PetService petService;
	private final RoomBookingService roomBookingService;

	@Autowired
	public RoomBookingController(PetService petService, RoomBookingService roomBookingService) {
		this.petService = petService;
		this.roomBookingService = roomBookingService;
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
	public String processNewVisitForm(@Valid RoomBooking r, BindingResult result) {

		boolean c1 = r.getCheckIn().isBefore(LocalDate.now());
		boolean c2 = r.getCheckOut().isBefore(r.getCheckIn());
		if (result.hasErrors())
			return "pets/createRoomBookingForm";
		try {
			this.roomBookingService.saveRoom(r);
		} catch (IncorrectDatesException e1) {
			if (c1 && c2) {
				result.rejectValue("checkIn", "error.IncorrectCheckIn", "Check in's date must be after today's date");
				result.rejectValue("checkOut", "error.IncorrectCheckOut", "Check out date must be after check in date");
				return "pets/createRoomBookingForm";
			} else if (!c1 && c2) {
				result.rejectValue("checkOut", "error.IncorrectCheckOut", "Check out date must be after check in date");
				return "pets/createRoomBookingForm";
			} else if (c1 && !c2) {
				result.rejectValue("checkIn", "error.IncorrectCheckIn", "Check in's date must be after today's date");
				return "pets/createRoomBookingForm";
			}
		} catch (ConcurrentBookingException e2) {
			result.rejectValue("checkIn", "error.ConcurrentBooking", "You can not book a room concurrently");
			result.rejectValue("checkOut", "error.ConcurrentBooking", "You can not book a room concurrently");
			return "pets/createRoomBookingForm";
		}

		return "redirect:/owners/{ownerId}";
	}
}
