package com.example.asm_andapi103_ph37268;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.asm_andapi103_ph37268.Handel.Item_Handel_Product;
import com.example.asm_andapi103_ph37268.adapter.AdapterProducts;
import com.example.asm_andapi103_ph37268.adapter.AdapterTypename;
import com.example.asm_andapi103_ph37268.models.CartResponseBody;
import com.example.asm_andapi103_ph37268.models.Product;
import com.example.asm_andapi103_ph37268.models.Response;
import com.example.asm_andapi103_ph37268.models.ResponseCart;
import com.example.asm_andapi103_ph37268.models.TypeName;
import com.example.asm_andapi103_ph37268.service.HttpRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class Home extends Fragment implements Item_Handel_Product {
private ViewFlipper viewFlipper;
private TextView prop;
private ImageView imageView;

    private ArrayList<Product> ds;

    private ArrayList<TypeName>ds1;
    private HttpRequest httpRequest;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView1;
    private AdapterProducts adapter;
    private AdapterTypename adapterTypename;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewFlipper = view.findViewById(R.id.slider);
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(2000);
        viewFlipper.startFlipping();
        imageView=view.findViewById(R.id.avatar);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(), Projectname.class);
                startActivity(intent);
            }
        });
        prop=view.findViewById(R.id.txtprops);
        prop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          opendialogthem();
            }
        });
        recyclerView=view.findViewById(R.id.rcv2_frm_main_user);
        recyclerView1=view.findViewById(R.id.rcv1_frm_main_user);

        httpRequest=new HttpRequest();
        httpRequest.callApi().getlisttypename().enqueue(getlistTypename);
        httpRequest.callApi().getlistProduct().enqueue(getlistProduct);

        return view;
    }
    private void getData(ArrayList<Product> ds){
        adapter = new AdapterProducts(getContext(), ds, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onAddToCartClick(Product product) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

// Kiểm tra xem người dùng đã đăng nhập hay chưa
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();


            // Tạo đối tượng request body
            CartResponseBody requestBody = new CartResponseBody(userId, product.getID(), 1); // Ví dụ, số lượng sản phẩm là 1

            // Gọi API để thêm sản phẩm vào giỏ hàng
            httpRequest.callApi().addToCart(requestBody).enqueue(new Callback<ResponseCart>() {


                @Override
                public void onResponse(Call<ResponseCart> call, retrofit2.Response<ResponseCart> response) {
                    if (response.isSuccessful()) {

                        // Xử lý khi thêm vào giỏ hàng thành công
                        Toast.makeText(getContext(), "Added to cart successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            // Xử lý khi thêm vào giỏ hàng không thành công
                            String errorBody = response.errorBody().string(); // Get error message from error body
                            Log.e("API Error", "Error message: " + errorBody);
                            Toast.makeText(getContext(), "Failed to add to cart: " + errorBody, Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            Log.e("API Error", "IOException: " + e.getMessage());
                            Toast.makeText(getContext(), "Failed to add to cart: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseCart> call, Throwable t) {
                    // Xử lý khi gọi API thất bại (ví dụ: lỗi mạng)
                    Log.d(".....", "onFailure: " + t.getMessage());
                    Toast.makeText(getContext(), "Added to cart successfully", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }









    private void getData1(ArrayList<TypeName> ds){
        adapterTypename=new AdapterTypename(getContext(),ds);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView1.setAdapter(adapterTypename);
    }
    Callback<Response<ArrayList<TypeName>>> getlistTypename=new Callback<Response<ArrayList<TypeName>>>() {
        @Override
        public void onResponse(Call<Response<ArrayList<TypeName>>> call, retrofit2.Response<Response<ArrayList<TypeName>>> response) {
            if (response.isSuccessful()){
                if (response.body().getStatus()==200){
                    ds1=response.body().getData();
                 getData1(ds1);
//                    Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                }
            }
        }


        @Override
        public void onFailure(Call<Response<ArrayList<TypeName>>> call, Throwable t) {
            Log.d("Diss","" + t.getMessage());
        }
    };

    Callback<Response<ArrayList<Product>>> getlistProduct=new Callback<Response<ArrayList<Product>>>() {
        @Override
        public void onResponse(Call<Response<ArrayList<Product>>> call, retrofit2.Response<Response<ArrayList<Product>>> response) {
            if (response.isSuccessful()){
                if (response.body().getStatus()==200){
                    ds=response.body().getData();
                    getData(ds);
//                    Toast.makeText(getContext(),"", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<Response<ArrayList<Product>>> call, Throwable t) {
            Log.d("Diss","" + t.getMessage());
        }
    };
Callback<Response<Product>> responseCallback=new Callback<Response<Product>>() {
    @Override
    public void onResponse(Call<Response<Product>> call, retrofit2.Response<Response<Product>> response) {
        if (response.isSuccessful()){
            if (response.body().getStatus()==200){
                httpRequest.callApi().getlistProduct().enqueue(getlistProduct);
            }
        }
    }

    @Override
    public void onFailure(Call<Response<Product>> call, Throwable t) {

    }
};
    private void opendialogthem(){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        LayoutInflater inflater=getLayoutInflater();
        View view=inflater.inflate(R.layout.dialogadd,null);
        builder.setView(view);
        Dialog dialog=builder.create();
        dialog.show();
        //ánh xa
        EditText txtimage = view.findViewById(R.id.edt_imageeidt);
        EditText txtname = view.findViewById(R.id.edt_teneidt);
        EditText txtsize = view.findViewById(R.id.edt_sizeeidt);
        EditText txtprice = view.findViewById(R.id.edt_priceeidt);
        EditText txtdiscrip = view.findViewById(R.id.edt_discripeidt);
        Button btnthem = view.findViewById(R.id.btn_edit);
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String image=txtimage.getText().toString();
                String ten=txtname.getText().toString();
                String size=txtsize.getText().toString();
                String price=txtprice.getText().toString();
                String discrip=txtdiscrip.getText().toString();
                Product dis=new Product();
                dis.setImage(image);
                dis.setName(ten);
                dis.setSize(size);
                dis.setPrice(price);
                dis.setDiscrip(discrip);
                httpRequest.callApi().addProduct(dis).enqueue(responseCallback);
                dialog.dismiss(); // Đóng dialog sau khi thêm thành công
                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void showDialogEdit(Product student) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialogeditdistributor, null); // Thay your_dialog_layout bằng layout của dialog của bạn
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        EditText edtimage = dialogView.findViewById(R.id.edt_imageeidt);
        edtimage.setText(student.getImage());
        EditText etName = dialogView.findViewById(R.id.edt_teneidt); // Thay R.id.etName bằng ID của EditText trong layout của bạn
        etName.setText(student.getName());
        EditText etSize = dialogView.findViewById(R.id.edt_sizeeidt);
        etSize.setText(student.getSize());
        EditText etprice = dialogView.findViewById(R.id.edt_priceeidt);
        etprice.setText(student.getPrice());
        EditText etdiscrip = dialogView.findViewById(R.id.edt_discripeidt);
        etdiscrip.setText(student.getDiscrip());
        Button btnSubmit = dialogView.findViewById(R.id.btn_edit); // Thay R.id.btnSubmit bằng ID của Button trong layout của bạn
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String image=edtimage.getText().toString().trim();
                String name = etName.getText().toString().trim();
                String size=etSize.getText().toString().trim();
                String discrip=etdiscrip.getText().toString().trim();
                String price=etprice.getText().toString().trim();
                if (name.isEmpty()) {
                    Toast.makeText(getContext(), "You must enter a name", Toast.LENGTH_SHORT).show();
                } else {
                    Product distributor1=new Product();
                    distributor1.setImage(image);
                    distributor1.setName(name);
                    distributor1.setSize(size);
                    distributor1.setDiscrip(discrip);
                    distributor1.setPrice(price);
                    httpRequest.callApi()
                            .update(student.getID(), distributor1)
                            .enqueue(responseCallback);
                    Toast.makeText(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();

                }
            }
        });

        alertDialog.show();
    }

    @Override
    public void Delete(Product product) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Confirm delete");
        builder.setMessage("Are you sure you want to delete?");
        builder.setPositiveButton("yes", (dialog, which) -> {
            httpRequest.callApi()
                    .delete(product.getID())
                    .enqueue(responseCallback);
            Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("no", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.show();
    }

    @Override
    public void Update(Product product) {
        showDialogEdit(product);
    }

    // Lưu cartId vào SharedPreferences



}