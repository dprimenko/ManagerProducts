package com.example.managerproducts.interfaces;

import com.example.managerproducts.adapter.ProductAdapter;
import com.example.managerproducts.model.Product;

import java.util.ArrayList;

/**
 * Created by dprimenko on 16/12/16.
 */
public interface IMultiListProductMvp {

    interface View extends IListPresenter.View  {
        int ADD_PRODUCT_REQUEST = 10;
        int EDIT_PRODUCT_REQUEST = 11;
    }

    interface Presenter {
        void restoreProducts(ArrayList<Product> products);
        void addProduct(Product product);
        void updateProduct(Product product);
    }
}
