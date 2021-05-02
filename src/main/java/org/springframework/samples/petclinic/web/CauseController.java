package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.CauseService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CauseController {

	@Autowired
	private CauseService causeService;

	@Autowired
	private UserService userService;

	@GetMapping(value = "/causes")
	public String causesList(ModelMap m) {

		Iterable<Cause> causes = causeService.findAll();
		m.addAttribute("causes", causes);
		m.addAttribute("cause", new Cause());
		return "causes/causesList";

	}

	@GetMapping(value = "/causes/{causeId}")
	public String causeDetails(ModelMap m, @PathVariable("causeId") int id) {

		Cause cause = causeService.findCauseById(id);
		Double donated = causeService.findBudgetTarget(id);
		m.addAttribute("cause", cause);
		m.addAttribute("donated", donated);
		return "causes/causeDetails";
	}

	@GetMapping(value = "/causes/new")
	public String initFormNewCause(ModelMap m) {
		m.addAttribute("cause", new Cause());
		return "/causes/newCauseForm";
	}

	@PostMapping(value = "/causes/save")
	public String saveNewCause(@Valid Cause c, BindingResult r, ModelMap m) {

		if (r.hasErrors()) {
			return "causes/newCauseForm";
		} else {
			c.setOpen(true);
			causeService.saveCause(c);
			m.addAttribute("message", "Cause created correctly");
			return causesList(m);
		}
	}

	@GetMapping(value = "/causes/{causeId}/newDonation")
	public String initDonation(@PathVariable("causeId") int id, ModelMap m) {
		Donation d = new Donation();
		m.addAttribute("donation", d);
		return "causes/newDonation";
	}

	@PostMapping(value = "/causes/{causeId}/newDonation")
	public String saveDonation(@PathVariable("causeId") int id, @Valid Donation d, BindingResult r, ModelMap m) {

		if (r.hasErrors()) {
			return "causes/newDonation";
		} else {
			d.setCause(causeService.findCauseById(id));
			d.setDate(LocalDate.now());
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			User user = userService.findUser(username).get();
			d.setUser(user);
			causeService.saveDonation(d);
			m.addAttribute("message", "Donation made correctly");
			return "redirect:/causes";
		}
	}
}
