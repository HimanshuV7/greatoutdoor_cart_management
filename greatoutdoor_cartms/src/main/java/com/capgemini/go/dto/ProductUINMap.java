package com.capgemini.go.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProductUINMap {

    @Id
    private String productId;

    private String productUin;

    private boolean productIsPresent;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductUin() {
        return productUin;
    }

    public void setProductUin(String productUin) {
        this.productUin = productUin;
    }

    public boolean isProductIsPresent() {
        return productIsPresent;
    }

    public void setProductIsPresent(boolean productIsPresent) {
        this.productIsPresent = productIsPresent;
    }
}
