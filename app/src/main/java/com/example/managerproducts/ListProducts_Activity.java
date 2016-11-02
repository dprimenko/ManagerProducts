package com.example.managerproducts;

import android.app.ListActivity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.managerproducts.adapter.ProductAdapter;
import com.example.managerproducts.adapter.ProductAdapterRecycler;

public class ListProducts_Activity extends AppCompatActivity {
    ;
    private FloatingActionButton btnAddItem;
    private ProductAdapterRecycler adapter;
    private RecyclerView rcvProduct;

    private static final int ADD_PRODUCT_REQUEST = 0;
    private static final int EDIT_PRODUCT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_products);

        //region RecyclerView Adapter
        adapter = new ProductAdapterRecycler(this, 0);
        rcvProduct = (RecyclerView)findViewById(R.id.rcvProduct);
        rcvProduct.setLayoutManager(new LinearLayoutManager(this));
        rcvProduct.setAdapter(adapter);
        //endregion

        btnAddItem = (FloatingActionButton) findViewById(R.id.btnAddItem);

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListProducts_Activity.this, ManageProduct_Activity.class);
                startActivityForResult(intent, ADD_PRODUCT_REQUEST);
            }
        });
    }

    /*
     *  Método que infla el menu en la Activity
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;

        switch (item.getItemId()) {
            case R.id.action_sort_alphabetically:
                sortProductList(3);
                break;
            case R.id.action_settings_general:
                intent = new Intent(this, GeneralSettings_Activity.class);
                startActivity(intent);
                break;
            case R.id.action_settings_account:
                intent = new Intent(this, AccountSettings_Activity.class);
                startActivity(intent);
                break;
            case R.id.action_logout:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case ADD_PRODUCT_REQUEST:

                if (resultCode == RESULT_OK) {
                    // Añadir el producto
                }

                break;
            case EDIT_PRODUCT_REQUEST:

                if (resultCode == RESULT_OK) {
                    // Añadir el producto
                }

                break;
        }
    }

    public void sortProductList(int sortRequestCode) {
        adapter.sortList(sortRequestCode);
    }
}
