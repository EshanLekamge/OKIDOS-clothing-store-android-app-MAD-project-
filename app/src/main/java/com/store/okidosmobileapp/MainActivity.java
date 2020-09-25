package com.store.okidosmobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{
    Button loginbutton;
    Button insertProductsbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginbutton = findViewById(R.id.loginbtn);
        insertProductsbutton = findViewById(R.id.addnewProductsbtn);

    }

    @Override
    protected void onResume() {
        super.onResume();
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, HomeFragment.class);
                startActivity(intent);

            }
        });

        insertProductsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent intent = new Intent(MainActivity.this, InsertProduct.class);
                startActivity(intent);

            }
        });
    }
}