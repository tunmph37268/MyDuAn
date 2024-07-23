package com.example.asm_andapi103_ph37268.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.asm_andapi103_ph37268.R;
import com.example.asm_andapi103_ph37268.models.Product;
import com.example.asm_andapi103_ph37268.models.ProductItem;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private Context context;
    private List<ProductItem> products;

    public CartAdapter(Context context) {
        this.context = context;
        this.products = new ArrayList<>();
    }

    public void setProducts(List<ProductItem> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        ProductItem product = products.get(position);
        Product pro=product.getProductId();
        holder.nameTextView.setText(pro.getName());
        holder.priceTextView.setText("Price: "+String.valueOf(pro.getPrice())+"$");
        Glide.with(holder.imgpro.getContext())
                .load(pro.getImage()).into(holder.imgpro);
        holder.quantityTextView.setText("SL: "+String.valueOf(product.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, priceTextView, quantityTextView;
        ImageView imgpro;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.txtProductName);
            priceTextView = itemView.findViewById(R.id.txtProductPrice);
            quantityTextView = itemView.findViewById(R.id.txtProductQuantity);
            imgpro=itemView.findViewById(R.id.imgProduct);
        }
    }
}


