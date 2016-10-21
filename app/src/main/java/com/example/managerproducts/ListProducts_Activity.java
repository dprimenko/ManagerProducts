package com.example.managerproducts;

import android.app.ListActivity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.view.View;

import com.example.managerproducts.adapter.ProductAdapter;
import com.example.managerproducts.adapter.ProductAdapterA;
import com.example.managerproducts.adapter.ProductAdapterB;

public class ListProducts_Activity extends ListActivity {

    //private ArrayAdapter<Product> productAdapter;
    private FloatingActionButton btnAddItem;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_products);

        /* Adapter Génerico */
        /* productAdapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1,
                ((ProductApplication)getApplication()).getListProducts());
        */


        /* Adapter Personalizado */
        adapter = new ProductAdapter (this);
        getListView().setAdapter(adapter);

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
