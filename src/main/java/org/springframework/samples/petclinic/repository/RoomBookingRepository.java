package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.RoomBooking;

public interface RoomBookingRepository extends Repository<RoomBooking, Integer> {
    @Query("SELECT r FROM RoomBooking r WHERE r.pet.id=:id")
    public Collection<RoomBooking> findByPetId(@Param("id") Integer id);

    public void save(RoomBooking r);

    public List<RoomBooking> findAll();
}
