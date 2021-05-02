package org.springframework.samples.petclinic.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.User;

public interface UserRepository extends Repository<User, String> {

    @Query("SELECT user FROM User user WHERE user.username =?1")
    User findByUsername(String username);

    void save(User user);

    User findById(String username);

}
