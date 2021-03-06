package org.kumar.primestarter.views;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

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
public class ProductView implements Serializable {

    @Autowired
    private ProductRepository productRepository;

    private Long id;
    private Product product;

    @PostConstruct
    public void check() {
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

        System.out.println("-------------" + locale.getCountry());
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        for (Map.Entry<String, Object> entry : sessionMap.entrySet()) {
            System.out.println(entry.getKey() + " ----------- " + entry.getValue());

            if (entry.getKey().equals("com.sun.faces.renderkit.ServerSideStateHelper.LogicalViewMap")) {

            }
        }
    }

    public void init() {
        if (getId() != null) {
            product = productRepository.findById(getId()).orElse(null);
        } else {
            product = new Product();
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

    public void save() {
        productRepository.save(product);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("product saved successfully"));
    }

}
