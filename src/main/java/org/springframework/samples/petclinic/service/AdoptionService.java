package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.AdoptionApplication;
import org.springframework.samples.petclinic.model.AdoptionRequest;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.repository.AdoptionApplicationRepository;
import org.springframework.samples.petclinic.repository.AdoptionRequestRepository;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdoptionService {

	private AdoptionApplicationRepository adoptionApplicationRepository;
	private AdoptionRequestRepository adoptionRequestRepository;
	private PetService petService;

	@Autowired
	public AdoptionService(AdoptionApplicationRepository adoptionApplicationRepository,
			AdoptionRequestRepository adoptionRequestRepository, PetService petService) {
		this.adoptionApplicationRepository = adoptionApplicationRepository;
		this.adoptionRequestRepository = adoptionRequestRepository;
		this.petService = petService;
	}

	@Transactional
	public void saveAdoptionApplication(AdoptionApplication adoptionApplication) throws DataAccessException {
		adoptionApplicationRepository.save(adoptionApplication);
	}

	@Transactional(readOnly = true)
	public Collection<AdoptionApplication> listAdoptionApplicationsByRequestId(Integer reqId)
			throws DataAccessException {
		return this.adoptionApplicationRepository.findByRequestId(reqId);
	}

	@Transactional(readOnly = true)
	public AdoptionApplication findApplicationById(Integer id) {
		return this.adoptionApplicationRepository.findById(id);
	}

	@Transactional
	public void saveAdoptionRequest(AdoptionRequest adoptionRequest) throws DataAccessException {
		adoptionRequestRepository.save(adoptionRequest);
	}

	@Transactional(readOnly = true)
	public Collection<AdoptionRequest> listAdoptionRequests() throws DataAccessException {
		return adoptionRequestRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Collection<AdoptionRequest> listMyAdoptionRequests(Integer ownerId) throws DataAccessException {
		return adoptionRequestRepository.findByOwnerId(ownerId);
	}

	@Transactional(readOnly = true)
	public AdoptionRequest findRequestById(Integer id) {
		return this.adoptionRequestRepository.findById(id);
	}

	@Transactional
	public void confirmApplication(AdoptionApplication application)
			throws DataAccessException, DuplicatedPetNameException {
		application.setApproved(true);
		adoptionApplicationRepository.save(application);
		Pet pet = application.getRequest().getPet();
		pet.setOwner(application.getFutureOwner());
		petService.savePet(pet);
	}

}
