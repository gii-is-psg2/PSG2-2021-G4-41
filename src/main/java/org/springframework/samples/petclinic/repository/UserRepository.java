package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.User;

public interface UserRepository extends CrudRepository<User, String> {

    @Query("SELECT user FROM User user WHERE user.username =?1")
    User findByUsername(String username);

}
