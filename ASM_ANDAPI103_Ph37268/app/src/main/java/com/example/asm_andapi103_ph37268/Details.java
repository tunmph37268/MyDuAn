package com.example.asm_andapi103_ph37268;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.asm_andapi103_ph37268.models.Product;

import java.util.ArrayList;

public class Details extends AppCompatActivity {
private ImageView imageView;
private Button btadd;
private ArrayList<Product> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        btadd=findViewById(R.id.buttonadd);
        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Details.this,Home.class);
                startActivity(intent);
            }
        });
    imageView=findViewById(R.id.imageView3);
    imageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(Details.this, HomeActivity.class);
            startActivity(intent);
        }
    });
    ImageView image1=findViewById(R.id.imageView5);
    TextView name1=findViewById(R.id.textView2);
    TextView size1=findViewById(R.id.textView4);
    TextView price1=findViewById(R.id.textView5);
    TextView discrip1=findViewById(R.id.textView7);
  Intent intent=getIntent();
   String image=intent.getStringExtra("image");
  String name=intent.getStringExtra("name");
String size=intent.getStringExtra("size");
String price=intent.getStringExtra("price");
String dsicrip=intent.getStringExtra("discrip");
        Glide.with(this).load(image).into(image1);
        name1.setText(name);
        size1.setText("Size:" +size);
        price1.setText("Price:" +price+"$");
        discrip1.setText(dsicrip);
    }
}