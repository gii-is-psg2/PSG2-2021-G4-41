package org.springframework.samples.petclinic.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.AdoptionRequest;

public interface AdoptionRequestRepository extends CrudRepository<AdoptionRequest,Integer> {
    @Query("SELECT request FROM AdoptionRequest request WHERE request.owner.id = ?1 ORDER BY request.timestamp")
    Collection<AdoptionRequest> findByOwnerId(Integer id);
}
