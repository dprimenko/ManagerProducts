package com.example.managerproducts;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.managerproducts.interfaces.IAddProductMvp;
import com.example.managerproducts.presenter.AddProductPresenter;

/**
 * Created by dprimenko on 20/10/16.
 */
public class ManageProduct_Activity extends AppCompatActivity implements IAddProductMvp.View{

    private static final int  MAX_VALUE_PRICE = 8;
    private static final int  MAX_VALUE_CONCENTRATION = 5;
    private EditText edtName;
    private EditText edtDescription;
    private EditText edtBrand;
    private EditText edtPrice;
    private EditText edtConcentration;
    private Button btnAddProduct;
    private IAddProductMvp.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_product);

        presenter = new AddProductPresenter(this);

        edtName = (EditText) findViewById(R.id.edtName);
        edtDescription = (EditText) findViewById(R.id.edtDescription);
        edtBrand = (EditText) findViewById(R.id.edtBrand);
        edtPrice = (EditText) findViewById(R.id.edtPrice);
        edtConcentration = (EditText) findViewById(R.id.edtConcentration);
        btnAddProduct = (Button) findViewById(R.id.btnAddProduct);

        edtPrice.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MAX_VALUE_PRICE)});
        edtConcentration.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MAX_VALUE_CONCENTRATION)});
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edtName.getText().toString();
                String description = edtDescription.getText().toString();
                String brand = edtBrand.getText().toString();
                String price = edtPrice.getText().toString();
                String concentration = edtConcentration.getText().toString();

                presenter.validateFields(name, description, brand, price, concentration);
            }
        });
    }

    @Override
    public void setMessageError(int messageError) {
        Snackbar.make(findViewById(R.id.addProductLayout), messageError, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void startListProductsActivity() {
        Intent intent = new Intent(this, ListProducts_Activity.class);
        startActivity(intent);
    }
}
