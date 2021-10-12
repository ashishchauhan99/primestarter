package org.kumar.primestarter.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    @ManyToOne
    private Brand brand;
    @ManyToOne
    private Category category;
    private Integer modelYear;
    private BigDecimal listPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

}