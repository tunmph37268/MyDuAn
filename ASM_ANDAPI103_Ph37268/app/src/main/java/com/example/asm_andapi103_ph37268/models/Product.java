package com.example.asm_andapi103_ph37268.models;


import com.google.gson.annotations.SerializedName;

public class Product {
@SerializedName("_id")
    private String ID;
    private String image, name,size,price,id_typename,discrip;
    private String createAt,updateAt;

    public Product() {
    }

    public Product(String ID, String image, String name, String size, String price, String id_typename, String discrip) {
        this.ID = ID;
        this.image = image;
        this.name = name;
        this.size = size;
        this.price = price;
        this.id_typename = id_typename;
        this.discrip = discrip;
    }

    public String getDiscrip() {
        return discrip;
    }

    public void setDiscrip(String discrip) {
        this.discrip = discrip;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId_typename() {
        return id_typename;
    }

    public void setId_typename(String id_typename) {
        this.id_typename = id_typename;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }
}
