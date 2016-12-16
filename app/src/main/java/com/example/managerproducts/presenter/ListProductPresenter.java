package com.example.managerproducts.presenter;

import com.example.managerproducts.interfaces.IListProductMvp;
import com.example.managerproducts.model.Product;

import java.util.ArrayList;

/**
 * Created by usuario on 16/12/16.
 */

public class ListProductPresenter implements IListProductMvp.Presenter {

    private IListProductMvp.View view;

    public ListProductPresenter(IListProductMvp.View view) {
        this.view = view;
    }

    @Override
    public void deleteMultipleProducts() {

    }
}
