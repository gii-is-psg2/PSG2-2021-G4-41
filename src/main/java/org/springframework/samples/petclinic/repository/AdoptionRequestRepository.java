package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.AdoptionRequest;

public interface AdoptionRequestRepository extends Repository<AdoptionRequest, Integer> {
    @Query("SELECT request FROM AdoptionRequest request WHERE request.owner.id = ?1 ORDER BY request.timestamp")
    Collection<AdoptionRequest> findByOwnerId(Integer id);

    void save(AdoptionRequest adoptionRequest);

    AdoptionRequest findById(Integer id);

    Collection<AdoptionRequest> findAll();
}
