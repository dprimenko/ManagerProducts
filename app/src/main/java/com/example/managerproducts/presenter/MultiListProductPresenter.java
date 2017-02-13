package com.example.managerproducts.presenter;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;

import com.example.managerproducts.db.DatabaseManager;
import com.example.managerproducts.interfaces.IListPresenter;
import com.example.managerproducts.interfaces.IMultiListProductMvp;
import com.example.managerproducts.model.Category;
import com.example.managerproducts.model.Product;
import com.example.managerproducts.provider.ManageProductContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by usuario on 16/12/16.
 */

public class MultiListProductPresenter extends ListPresenterImpl implements IMultiListProductMvp.Presenter {

    private IListPresenter.View view;

    public MultiListProductPresenter(IListPresenter.View view) {
        super(view, IListPresenter.PRODUCT);
        this.view = view;
    }

    @Override
    public void addProduct(Product product) {
        ContentValues contentValues = getContentValuesProduct(product);
        getContext().getContentResolver().insert(ManageProductContract.Product.CONTENT_URI, contentValues);
    }

    @Override
    public void restoreProducts(ArrayList<Product> products) {
        for (Product product:products) {
            addProduct(product);
        }
    }

    @Override
    public void updateProduct(Product product) {
    }

    private ContentValues getContentValuesProduct(Product product) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ManageProductContract.Product.NAME, product.getmName());
        contentValues.put(ManageProductContract.Product.DESCRIPTION, product.getmDescription());
        contentValues.put(ManageProductContract.Product.BRAND, product.getmBrand());
        contentValues.put(ManageProductContract.Product.DOSAGE, product.getmConcentration());
        contentValues.put(ManageProductContract.Product.PRICE, product.getmPrice());
        contentValues.put(ManageProductContract.Product.STOCK, product.getmStock());
        contentValues.put(ManageProductContract.Product.IMAGE, product.getmImage());
        contentValues.put(ManageProductContract.Product.sProductProjectionMap.get(ManageProductContract.Product.CATEGORY_ID), product.getmIdCategory());
        return contentValues;
    }

}
