package com.example.asm_andapi103_ph37268.models;

import com.google.gson.annotations.SerializedName;

public class ProductItem {

    @SerializedName("productId")
    private Product productId;

    @SerializedName("quantity")
    private int quantity;


    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

}
