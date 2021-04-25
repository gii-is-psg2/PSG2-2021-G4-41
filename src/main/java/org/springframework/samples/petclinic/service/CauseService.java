package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.repository.CauseRepository;
import org.springframework.samples.petclinic.repository.DonationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CauseService {
	
	@Autowired
	private CauseRepository causeRepository;
	
	@Autowired
	private DonationRepository donationRepository;

	@Autowired
	public CauseService(CauseRepository causeRepository, DonationRepository donationRepository) {
		this.causeRepository = causeRepository;
		this.donationRepository = donationRepository;
	}
	
	@Transactional(readOnly = true)
	public Iterable<Cause> findAll() throws DataAccessException {
		return causeRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<Cause> findCauseById(int id) throws DataAccessException {
		return causeRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Optional<Donation> findDonationById(int id) throws DataAccessException {
		return donationRepository.findById(id);
	}
	
	@Transactional
	public void saveCause(Cause cause) throws DataAccessException {
		causeRepository.save(cause);
	}	
	
	@Transactional
	public void saveDonation(Donation donation) throws DataAccessException {
		
		Integer donated = findBudgetTarget(donation.getCause().getId()) + donation.getAmount();
		Integer target = donation.getCause().getTarget();
		if(donated>=target) {
			causeRepository.delete(donation.getCause());
		}
		donationRepository.save(donation);
	}
	
	@Transactional(readOnly = true)
	public Integer findBudgetTarget(int id) throws DataAccessException {
		List<Donation> donations = causeRepository.findById(id).get().getDonations();
		Integer target = 0;
		for(Donation d: donations) {
			target = target + d.getAmount();
		}
		return target;
	}
	
	public List<Donation> findDonationsByCauseId(int causeId) {
		return causeRepository.findById(causeId).get().getDonations();
	}
}
