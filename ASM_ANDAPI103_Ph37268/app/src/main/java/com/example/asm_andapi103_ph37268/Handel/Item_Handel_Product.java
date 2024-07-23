package com.example.asm_andapi103_ph37268.Handel;

import com.example.asm_andapi103_ph37268.models.Product;

public interface Item_Handel_Product {
    void onAddToCartClick(Product product);

    public void Delete(Product product);
    public void Update(Product product);
}
