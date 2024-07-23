package com.example.asm_andapi103_ph37268.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.asm_andapi103_ph37268.Details;
import com.example.asm_andapi103_ph37268.Handel.Item_Handel_Product;
import com.example.asm_andapi103_ph37268.Home;
import com.example.asm_andapi103_ph37268.R;
import com.example.asm_andapi103_ph37268.Welcome;
import com.example.asm_andapi103_ph37268.models.Product;

import java.util.ArrayList;

public class AdapterProducts extends RecyclerView.Adapter<AdapterProducts.ViewHolder> {
    private Context context;
    private ArrayList<Product> ds;
    private Item_Handel_Product item;
    public interface ProductClickListener {
        void onAddToCartClick(Product product);
    }
    private ProductClickListener productClickListener;

    public void setProductClickListener(ProductClickListener listener) {
        this.productClickListener = listener;
    }


    public AdapterProducts(Context context, ArrayList<Product> ds, Item_Handel_Product item) {
        this.context = context;
        this.ds = ds;
        this.item = item;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_product,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product=ds.get(position);
        Glide.with(holder.image.getContext())
                .load(product.getImage()).into(holder.image);
        holder.tym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.Update(product);
            }
        });
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.Delete(product);
            }
        });
        holder.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               item.onAddToCartClick(product);

            }
        });
        holder.name.setText(product.getName());
        holder.size.setText("Size: "+ product.getSize());
        holder.price.setText(product.getPrice() +"$");
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), Details.class);
                intent.putExtra("image",product.getImage());
                intent.putExtra("name",product.getName());
                intent.putExtra("size",product.getSize());
                intent.putExtra("price",product.getPrice());
                intent.putExtra("discrip",product.getDiscrip());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
ImageView tym,image,cart;
TextView name,size,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tym=itemView.findViewById(R.id.heart);
            image=itemView.findViewById(R.id.item_img_rcv2);
            cart=itemView.findViewById(R.id.addtocart);
            name=itemView.findViewById(R.id.name);
            size=itemView.findViewById(R.id.item_size_rcv2);;
            price=itemView.findViewById(R.id.item_price_rcv);
        }
    }
}
