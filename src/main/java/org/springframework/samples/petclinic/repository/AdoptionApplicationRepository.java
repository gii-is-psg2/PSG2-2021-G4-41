package org.springframework.samples.petclinic.repository;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.AdoptionApplication;
import org.springframework.samples.petclinic.model.Pet;

public interface AdoptionApplicationRepository extends CrudRepository<AdoptionApplication,Integer> {

	AdoptionApplication findById(int id) throws DataAccessException;
}
