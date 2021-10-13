package org.kumar.primestarter.repository;

import org.kumar.primestarter.entity.PrimeUser;
import org.springframework.data.repository.CrudRepository;

public interface PrimeUserRepository extends CrudRepository<PrimeUser, Integer> {

    PrimeUser findByUsername(String username);

//    @Query("select u from User u where u.emailAddress = ?1")
//    PrimeUser findByEmailAddress(String emailAddress);

}
