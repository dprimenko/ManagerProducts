package com.example.managerproducts.interfaces;

import com.example.managerproducts.model.Product;

import java.util.ArrayList;

/**
 * Created by usuario on 16/12/16.
 */

public interface IListProductMvp {

    interface View {
        void showUndoSnackbar();
    }

    interface Presenter {
        void deleteMultipleProducts();
    }
}
