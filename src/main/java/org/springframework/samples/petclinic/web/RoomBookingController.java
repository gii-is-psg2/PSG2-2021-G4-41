package org.springframework.samples.petclinic.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.RoomBooking;
import org.springframework.samples.petclinic.service.RoomBookingService;
import org.springframework.samples.petclinic.service.exceptions.IncorrectDatesException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("roomBookings")
public class RoomBookingController {
	
	@Autowired
	private RoomBookingService roomBookingService;
	
	@GetMapping(value = "/list")
	public String roomList(ModelMap m) {
		String vista = "pets/createRoomBookingForm"; //No tengo claro si haría falta una vista para mostrar todas las rooms, lo dejo así
		Iterable<RoomBooking> rooms = roomBookingService.findAll();
		m.addAttribute("rooms", rooms);
		m.addAttribute("room", new RoomBooking());
		return vista;
	}
	
	@GetMapping(value = "/new")
	public String newRoom(ModelMap m) {
		String vista = "pets/createRoomBookingForm";
		m.addAttribute("room", new RoomBooking());
		return vista;
	}
	
	@PostMapping(value = "/save")
	public String saveRoom(@Valid RoomBooking r, BindingResult result, ModelMap m) throws IncorrectDatesException {
		String vista;
		
		if(result.hasErrors()) {
			m.addAttribute("room", r);
			vista = "pets/createRoomBookingForm";
		}
		else {
			roomBookingService.saveRoom(r);
			m.addAttribute("message", "Room reserved satisfactorily");
			vista = "pets/createRoomBookingForm"; //No tengo claro si haría falta una vista para mostrar todas las rooms, lo dejo así
		}
		return vista;
	}
	
	
	

}
