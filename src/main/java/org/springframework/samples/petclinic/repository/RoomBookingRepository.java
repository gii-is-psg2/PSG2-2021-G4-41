package org.springframework.samples.petclinic.repository;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.RoomBooking;

public interface RoomBookingRepository extends CrudRepository<RoomBooking, Integer> {
	@Query("SELECT r FROM RoomBooking r WHERE r.pet.id=:id")
    public Collection<RoomBooking> findByPetId(@Param("id") Integer id);
}
