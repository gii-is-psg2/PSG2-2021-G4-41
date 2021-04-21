package org.springframework.samples.petclinic.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Donation;

public interface DonationRepository extends Repository<Donation, Integer> {

	Donation findById(int id) throws DataAccessException;

	
	void save(Donation donation) throws DataAccessException;
	
	
	void delete(Donation donation);
}
