package org.kumar.primestarter.views;

import java.util.List;
import java.util.Map;

import org.kumar.primestarter.entity.Product;
import org.kumar.primestarter.repository.ProductRepository;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

public class LazyProductsDataModel extends LazyDataModel<Product> {

    private static final long serialVersionUID = 1L;

    private transient ProductRepository productRepository;

    public LazyProductsDataModel(ProductRepository productRepository) {
        this.productRepository = productRepository;
        setRowCount((int) this.productRepository.count());
    }

    @Override
    public Product getRowData(String rowKey) {
        Long id = Long.parseLong(rowKey);
        return productRepository.findById(id).orElse(null);

    }

    @Override
    public List<Product> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        return productRepository.findProductsWithPaginationFilteringAndSorting(first, pageSize, sortBy, filterBy);
    }

    @Override
    public String getRowKey(Product product) {
        return String.valueOf(product.getId());
    }

}
