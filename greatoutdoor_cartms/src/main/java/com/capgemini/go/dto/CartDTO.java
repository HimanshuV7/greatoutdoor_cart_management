package com.capgemini.go.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "cartitems")
public class CartDTO implements Serializable {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "retailer_id", nullable = false)
    private String userId;

    @Column(name = "product_id", nullable = false)
    private String productId;
    @Column(name = "quantity", nullable = false)
    private int quantity;

    public CartDTO() {
    }

    public static String id(String userId, String productId) {
        String id = userId + "-" + productId;
        return id;
    }

    public CartDTO(String userId, String productId, int quantity) {
        String id= id(userId, productId);
        this.id=id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartDTO cartDTO = (CartDTO) o;
        return id.equals(cartDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
