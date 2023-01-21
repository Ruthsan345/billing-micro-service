package com.example.billingservice.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bill {

    boolean wholesalerOrRetailer;
    int clientId;
    String clientName;
    int quantity;
    int billAmount;
    int discountPercentage;
    float gstAmount;
    float grandBillAmount;

    Product product;

    public boolean isWholesalerOrRetailer() {
        return wholesalerOrRetailer;
    }

    public void setWholesalerOrRetailer(boolean wholesalerOrRetailer) {
        this.wholesalerOrRetailer = wholesalerOrRetailer;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(int billAmount) {
        this.billAmount = billAmount;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public float getGstAmount() {
        return gstAmount;
    }

    public void setGstAmount(float gstAmount) {
        this.gstAmount = gstAmount;
    }

    public float getGrandBillAmount() {
        return grandBillAmount;
    }

    public void setGrandBillAmount(float grandBillAmount) {
        this.grandBillAmount = grandBillAmount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}
