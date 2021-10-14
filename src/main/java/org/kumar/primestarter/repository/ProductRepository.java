package org.kumar.primestarter.repository;

import org.kumar.primestarter.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>, ProductRepositoryCustom {

}
