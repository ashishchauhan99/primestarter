package org.kumar.primestarter.repository;

import org.kumar.primestarter.entity.PrimeUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<PrimeUser, Integer> {

    PrimeUser findByUsername(String username);

}
