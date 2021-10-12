package org.kumar.primestarter.repository;

import org.kumar.primestarter.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
