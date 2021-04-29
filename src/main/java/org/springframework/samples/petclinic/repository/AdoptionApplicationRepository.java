package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.AdoptionApplication;

public interface AdoptionApplicationRepository extends Repository<AdoptionApplication, Integer> {

	@Query("SELECT application FROM AdoptionApplication application WHERE application.request.id = ?1 ORDER BY application.timestamp")
	Collection<AdoptionApplication> findByRequestId(int id) throws DataAccessException;

	AdoptionApplication findById(int id) throws DataAccessException;

	void save(AdoptionApplication adoptionApplication);
}
