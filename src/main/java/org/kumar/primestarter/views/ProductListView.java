package org.kumar.primestarter.views;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.kumar.primestarter.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Named
@ViewScoped
public class ProductListView {

    @Autowired
    private ProductRepository productRepository;
    private LazyProductsDataModel lazyProductsDataModel;

    @PostConstruct
    private void init() {
        lazyProductsDataModel = new LazyProductsDataModel(productRepository);
    }

    public LazyProductsDataModel getLazyProductsDataModel() {
        return lazyProductsDataModel;
    }

}
