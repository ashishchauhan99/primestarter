package org.kumar.primestarter.views;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.kumar.primestarter.entity.Product;
import org.kumar.primestarter.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Named
@ViewScoped
public class ProductListView {

    @Autowired
    private ProductRepository productRepository;
    private LazyProductsDataModel lazyProductsDataModel;

    private Long id;

    private Product selectedProduct;

    @PostConstruct
    private void init() {
        lazyProductsDataModel = new LazyProductsDataModel(productRepository);
    }

    public LazyProductsDataModel getLazyProductsDataModel() {
        return lazyProductsDataModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public String redirectToProductView(Product product) throws IOException {
        setId(product.getId());
        return "/products/product.xhtml?faces-redirect=true&includeViewParams=true";
//        FacesContext.getCurrentInstance().getExternalContext().redirect("/products/product.xhtml");
    }

    public void deleteProduct() {
        productRepository.delete(selectedProduct);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("deleted successfully"));
    }

}
