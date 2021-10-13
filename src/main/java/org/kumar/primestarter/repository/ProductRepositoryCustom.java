package org.kumar.primestarter.repository;

import java.util.List;
import java.util.Map;

import org.kumar.primestarter.entity.Product;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

public interface ProductRepositoryCustom {

    List<Product> getProductsWithPaginationFilteringAndSorting(int first, int pageSize, Map<String, SortMeta> sortBy,
            Map<String, FilterMeta> filterBy);

}
