package org.kumar.primestarter.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.kumar.primestarter.entity.Product;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> getProductsWithPaginationFilteringAndSorting(int first, int pageSize,
            Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        return null;
    }

}
