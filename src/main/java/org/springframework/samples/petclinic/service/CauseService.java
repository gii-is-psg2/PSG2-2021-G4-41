package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.CauseRepository;
import org.springframework.samples.petclinic.repository.DonationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CauseService {
	
	private CauseRepository causeRepository;
	
	private DonationRepository donationRepository;

	@Autowired
	public CauseService(CauseRepository causeRepository, DonationRepository donationRepository) {
		this.causeRepository = causeRepository;
		this.donationRepository = donationRepository;
	}
	
	@Transactional(readOnly = true)
	public Cause findCauseById(int id) throws DataAccessException {
		return causeRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Donation findDonationById(int id) throws DataAccessException {
		return donationRepository.findById(id);
	}
	
	@Transactional
	public void saveCause(Cause cause) throws DataAccessException {
		causeRepository.save(cause);
	}	
	
	@Transactional
	public void saveDonation(Donation donation) throws DataAccessException {
		donationRepository.save(donation);
	}
	
	@Transactional(readOnly = true)
	public Integer findBudgetTarget(int id) throws DataAccessException {
		List<Donation> donations = causeRepository.findById(id).getDonations();
		Integer target = 0;
		for(Donation d: donations) {
			target = target + d.getAmount();
		}
		return target;
	}
	
	public List<Donation> findDonationsByCauseId(int causeId) {
		return causeRepository.findById(causeId).getDonations();
	}
}
