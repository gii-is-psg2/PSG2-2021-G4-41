package org.springframework.samples.petclinic.repository;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.AdoptionApplication;
import org.springframework.samples.petclinic.model.Pet;

public interface AdoptionApplicationRepository extends CrudRepository<AdoptionApplication,Integer> {

	@Query("SELECT application FROM AdoptionApplication application WHERE application.request.id = ?1 ORDER BY application.timestamp")
	AdoptionApplication findByRequestId(int id) throws DataAccessException;
}
