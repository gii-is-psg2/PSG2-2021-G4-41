package org.springframework.samples.petclinic.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.RoomBooking;

public interface RoomBookingRepository extends CrudRepository<RoomBooking, Integer> {

	
}
