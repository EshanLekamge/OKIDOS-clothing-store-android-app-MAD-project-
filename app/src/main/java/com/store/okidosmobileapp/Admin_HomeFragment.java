package com.store.okidosmobileapp;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.store.okidosmobileapp.ViewHolder.ProductViewHolder;


/**
 * A simple {@link Fragment} subclass.
 */
public class Admin_HomeFragment extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    private DatabaseReference ProductsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private String productChildKey;
    private FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity);

        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Product");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawer(GravityCompat.START);
                if (item.getItemId() == R.id.homenav) {

                    Intent intent = new Intent(Admin_HomeFragment.this, Admin_HomeFragment.class);
                    startActivity(intent);

                }
                if (item.getItemId() == R.id.account) {

                    Intent intent = new Intent(Admin_HomeFragment.this, MyAccountFragment.class);
                    startActivity(intent);

                }
                if (item.getItemId() == R.id.settings) {

                    Intent intent = new Intent(Admin_HomeFragment.this, AppSettingsFragment.class);
                    startActivity(intent);

                }
                return true;
            }
        });

        toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Product> options = new FirebaseRecyclerOptions.Builder<Product>().setQuery(ProductsRef, Product.class).build();

        FirebaseRecyclerAdapter<Product, ProductViewHolder> adapter = new FirebaseRecyclerAdapter<Product, ProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ProductViewHolder holder, int position, @NonNull final Product model)
            {
//
                holder.txtProductName.setText(model.getProductName());
                holder.txtProductDescription.setText(model.getProductDescription());
                holder.txtProductPrice.setText("Lkr "+model.getProductPrice());
                Picasso.get().load(model.getProductImage()).into(holder.imageView);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        CharSequence options[] = new CharSequence[]
                                {
                                        "Edit",
                                        "Delete"
                                };

                        AlertDialog.Builder builder = new AlertDialog.Builder(Admin_HomeFragment.this);
                        builder.setTitle("Cart Options:");

                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {

                                if(i == 0)
                                {
                                    Intent intent = new Intent(Admin_HomeFragment.this, Admin_UpdateProducts.class);
                                    intent.putExtra("productID", model.getProductID());

                                    startActivity(intent);
                                }
                                if(i == 1)
                                {
                                    ProductsRef.child(model.getProductID()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task)
                                        {

                                            if(task.isSuccessful())
                                            {
                                                Toast.makeText(Admin_HomeFragment.this, "Item Successfully Removed", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(Admin_HomeFragment.this, Admin_HomeFragment.class);
                                                startActivity(intent);
                                            }

                                        }
                                    });
                                }

                            }
                        });

                        builder.show();

                    }
                });


            }

            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
               View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_product_items_layout, parent, false);
               ProductViewHolder holder = new ProductViewHolder(view);
               return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }


}