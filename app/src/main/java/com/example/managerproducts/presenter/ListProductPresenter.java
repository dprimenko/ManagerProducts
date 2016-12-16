package com.example.managerproducts.presenter;

import com.example.managerproducts.interfaces.IListProductMvp;
import com.example.managerproducts.model.Product;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by usuario on 16/12/16.
 */

public class ListProductPresenter implements IListProductMvp.Presenter {

    private IListProductMvp.View view;

    public static final int ADD_PRODUCT_REQUEST = 10;
    public static final int EDIT_PRODUCT_REQUEST = 11;

    public ListProductPresenter(IListProductMvp.View view) {
        this.view = view;
    }

}
