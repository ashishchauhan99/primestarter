package org.kumar.primestarter.views;

import java.util.ArrayList;
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
    public List<Product> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public String getRowKey(Product product) {
        return String.valueOf(product.getId());
    }

}
