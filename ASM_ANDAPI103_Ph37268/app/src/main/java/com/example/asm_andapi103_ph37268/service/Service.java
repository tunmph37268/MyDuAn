package com.example.asm_andapi103_ph37268.service;

import com.example.asm_andapi103_ph37268.models.CartResponseBody;
import com.example.asm_andapi103_ph37268.models.Product;
import com.example.asm_andapi103_ph37268.models.Response;
import com.example.asm_andapi103_ph37268.models.ResponseCart;
import com.example.asm_andapi103_ph37268.models.TypeName;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {
    public static String BASE_URL="http://10.0.2.2:3000/api/";
    @GET("get-cart/{userId}")
    Call<ResponseCart> getCart(@Path("userId") String userId);
    @POST("add-to-cart")
    Call<ResponseCart> addToCart(@Body CartResponseBody body);
    @GET("get-list-product")
    Call<Response<ArrayList<Product>>> getlistProduct();
    @GET("get-product-by-name")
    Call<Response<ArrayList<Product>>> search(@Query("id")String id);
   @GET("get-list-typename")
   Call<Response<ArrayList<TypeName>>> getlisttypename();
    @POST("add-product")
    Call<Response<Product>> addProduct(@Body Product product);
    @PUT("update-product-by-id/{id}")
    Call<Response<Product>> update(@Path("id") String id, @Body Product product);
    @DELETE("delete-product-by-id/{id}")
    Call<Response<Product>>delete(@Path("id") String id);
}

