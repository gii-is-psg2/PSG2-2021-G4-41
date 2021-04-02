package org.springframework.samples.petclinic.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Specialty;

public interface SpecialtiesRepository extends Repository<Specialty, Integer> {

    Specialty findById(int id) throws DataAccessException;
}
