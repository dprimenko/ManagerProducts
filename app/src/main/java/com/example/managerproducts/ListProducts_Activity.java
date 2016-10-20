package com.example.managerproducts;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.managerproducts.model.Product;

public class ListProducts_Activity extends ListActivity {

    private ArrayAdapter<Product> productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_products);

        productAdapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1,
                ((ProductApplication)getApplication()).getListProducts());
        getListView().setAdapter(productAdapter);
    }
}
