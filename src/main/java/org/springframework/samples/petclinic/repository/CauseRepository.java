package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Cause;

public interface CauseRepository extends Repository<Cause, Integer> {

    List<Cause> findAll();

    Cause findById(int id);

    void save(Cause cause);

}
