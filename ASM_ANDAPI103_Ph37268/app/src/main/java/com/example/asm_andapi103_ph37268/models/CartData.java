package com.example.asm_andapi103_ph37268.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartData {

    @SerializedName("userId")
    private String userId;

    @SerializedName("products")
    private List<ProductItem> products;

    @SerializedName("totalPrice")
    private double totalPrice;

    public String getUserId() {
        return userId;
    }

    public List<ProductItem> getProducts() {
        return products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
