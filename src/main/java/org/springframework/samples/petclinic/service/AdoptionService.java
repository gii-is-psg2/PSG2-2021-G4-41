package org.springframework.samples.petclinic.service;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.AdoptionApplication;
import org.springframework.samples.petclinic.model.AdoptionRequest;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.repository.AdoptionApplicationRepository;
import org.springframework.samples.petclinic.repository.AdoptionRequestRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdoptionService {
	
	private AdoptionApplicationRepository adoptionApplicationRepository;
	private AdoptionRequestRepository adoptionRequestRepository;
	private VetRepository vetRepository;
	
	@Autowired
	public AdoptionService(AdoptionApplicationRepository adoptionApplicationRepository, AdoptionRequestRepository adoptionRequestRepository) {
		this.adoptionApplicationRepository=adoptionApplicationRepository;
		this.adoptionRequestRepository=adoptionRequestRepository;
	}
	
	@Transactional
	public void saveAdoptionApplication(AdoptionApplication adoptionApplication) throws DataAccessException {
		adoptionApplicationRepository.save(adoptionApplication);
	}

	public Collection<Adoption> listAdoptionApplicationsByRequestId(Integer reqId) throws DataAccessException {
		return adoptionApplicationRepository.findByRequestId(reqId);
	}
	
	@Transactional
	public void saveAdoptionRequest(AdoptionRequest adoptionRequest) throws DataAccessException {
		adoptionRequestRepository.save(adoptionRequest);
	}

	public Collection<AdoptionRequest> listAdoptionRequests() throws DataAccessException {
		return adoptionRequestRepository.findAll();
	}

	public Collection<AdoptionRequest> listMyAdoptionRequests(Integer ownerId) throws DataAccessException {
		return adoptionRequestRepository.findByOwnerId(ownerId);
	}
	
	
}
