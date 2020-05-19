package com.capgemini.go.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_product_map")
public class OrderProductMapDTO {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static String id(String orderId, String productId) {
        String id = orderId + "-" + productId;
        return id;
    }

    @Column(name = "product_uin")
    private String productUIN;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_status")
    private int productStatus;

    @Column(name = "gift_status")
    private int giftStatus;

    public String getProductUIN() {
        return productUIN;
    }

    public void setProductUIN(String productUIN) {
        this.productUIN = productUIN;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public int getGiftStatus() {
        return giftStatus;
    }

    public void setGiftStatus(int giftStatus) {
        this.giftStatus = giftStatus;
    }

   private int quantity;

   public int getQuantity(){
       return quantity;
   }

   public void setQuantity(int quantity){
       this.quantity=quantity;
   }

}
