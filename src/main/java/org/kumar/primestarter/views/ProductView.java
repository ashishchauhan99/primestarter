package org.kumar.primestarter.views;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.kumar.primestarter.entity.Product;
import org.kumar.primestarter.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Named
@ViewScoped
public class ProductView {

    @Autowired
    private ProductRepository productRepository;

    private Long id;
    private Product product;

    public void init() {
        if (getId() != null) {
            product = productRepository.findById(getId()).orElse(null);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
