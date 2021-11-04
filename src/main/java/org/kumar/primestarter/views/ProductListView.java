package org.kumar.primestarter.views;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.kumar.primestarter.entity.Product;
import org.kumar.primestarter.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

@Named
@ViewScoped
public class ProductListView {

    @Autowired
    private ProductRepository productRepository;
    private LazyProductsDataModel lazyProductsDataModel;

    private Long id;

    private Product selectedProduct;

    @Autowired
    private Environment env;

    @PostConstruct
    private void init() {
        lazyProductsDataModel = new LazyProductsDataModel(productRepository);

        System.out.println("+++++++++++++++++" + env.getProperty("joinfaces.jsf.state-saving-method"));
        System.out.println("+++++++++++++++++" + env.getProperty("joinfaces.jsf.serialize-server-state"));

        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        for (Map.Entry<String, Object> entry : sessionMap.entrySet()) {
            System.out.println(entry.getKey() + " ----------- " + entry.getValue());

            if (entry.getKey().equals("com.sun.faces.renderkit.ServerSideStateHelper.LogicalViewMap")) {

            }
        }

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
