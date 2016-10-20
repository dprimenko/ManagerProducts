package com.example.managerproducts;

import android.app.ListActivity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.managerproducts.model.Product;

public class ListProducts_Activity extends ListActivity {

    private ArrayAdapter<Product> productAdapter;
    private FloatingActionButton btnAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_products);

        productAdapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1,
                ((ProductApplication)getApplication()).getListProducts());
        getListView().setAdapter(productAdapter);

        btnAddItem = (FloatingActionButton) findViewById(R.id.btnAddItem);

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListProducts_Activity.this, AddProduct_Activity.class);
                startActivity(intent);
            }
        });
    }
}
