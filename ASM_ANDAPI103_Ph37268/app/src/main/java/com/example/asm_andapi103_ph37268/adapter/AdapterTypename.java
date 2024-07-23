package com.example.asm_andapi103_ph37268.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_andapi103_ph37268.R;
import com.example.asm_andapi103_ph37268.models.Product;
import com.example.asm_andapi103_ph37268.models.TypeName;

import java.util.ArrayList;

public class AdapterTypename extends RecyclerView.Adapter<AdapterTypename.ViewHolder> {
    private Context context;
    private ArrayList<TypeName> ds;

    public AdapterTypename(Context context, ArrayList<TypeName> ds) {
        this.context = context;
        this.ds = ds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_typename,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TypeName typeName=ds.get(position);
          holder.txtname.setText(typeName.getName());
    }

    @Override
    public int getItemCount() {
        return ds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtname;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname=itemView.findViewById(R.id.name);
        }
    }
}
