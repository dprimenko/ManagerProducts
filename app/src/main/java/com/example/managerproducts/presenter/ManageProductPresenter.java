package com.example.managerproducts.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.example.managerproducts.R;
import com.example.managerproducts.interfaces.IManageProductMvp;
import com.example.managerproducts.model.Product;

import java.util.Random;

/**
 * Created by dprimenko on 20/10/16.
 */
public class ManageProductPresenter implements IManageProductMvp.Presenter{

    private IManageProductMvp.View view;


    public ManageProductPresenter(IManageProductMvp.View view, Context context) {
        this.view = view;
    }

    @Override
    public void validateFields(String name, String description, String brand, String price, String concentration) {
        if ((TextUtils.isEmpty(name)) || (TextUtils.isEmpty(description)) || (TextUtils.isEmpty(brand)) || (TextUtils.isEmpty(price)) || (TextUtils.isEmpty(concentration))) {
            view.setMessageError(R.string.data_empty);
        }
        else {
            double priceParsed = Double.parseDouble(price);
            Product product = new Product(name, description, brand, concentration, priceParsed, new Random().nextInt(100), R.drawable.defecto);
            view.backToListProductFragment(product);
        }
    }
}
