package com.example.asm_andapi103_ph37268.models;

import com.google.gson.annotations.SerializedName;

public class TypeName {
    @SerializedName("_id")
    private String id;
    private String name;

    public TypeName() {
    }

    public TypeName(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
