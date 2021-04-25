package org.springframework.samples.petclinic.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.service.CauseService;
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
	
	@GetMapping(value = "/causes")
	public String causesList(ModelMap m) {
		
		Iterable<Cause> causes = causeService.findAll();
		m.addAttribute("causes", causes);
		m.addAttribute("cause", new Cause());
		return "causes/causesList";
		
	}
	
	@GetMapping(value = "/causes/{causeId}")
	public String causeDetails(ModelMap m, @PathVariable("causeId") int id) {
		
		Optional<Cause> cause = causeService.findCauseById(id);
		Integer donated = causeService.findBudgetTarget(id);
		m.addAttribute("cause", cause.get());
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
		
		if(r.hasErrors()) {
			m.addAttribute("cause", new Cause());
			return "causes/newCauseForm";
		}
		else {
			causeService.saveCause(c);
			m.addAttribute("message", "Cause created correctly");
			return causesList(m);
		}
	}
	
	@GetMapping(value= "/causes/{causeId}/newDonation")
	public String initDonation(@PathVariable("causeId") int id, ModelMap m) {
		
		Donation d = new Donation();
		d.setCause(causeService.findCauseById(id).get());
//		m.addAttribute("cause", causeService.findCauseById(id).get());
		m.addAttribute("donation", d);
		return "/causes/newDonation";
	}
	
	@PostMapping(value = "/causes/{causeId}/donate")
	public String saveDonation(@PathVariable("causeId") int id, @Valid Donation d, BindingResult r, ModelMap m) {
		
		if(r.hasErrors()) {
			m.addAttribute("donation", new Donation());
			return initDonation(id, m);
		}
		else {
			causeService.saveDonation(d);
			m.addAttribute("message", "Donation made correctly");
			return causesList(m);
		}
	}
	

}
