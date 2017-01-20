package com.example.managerproducts.interfaces;

import com.example.managerproducts.adapter.ProductAdapter;
import com.example.managerproducts.model.Product;

import java.util.ArrayList;

/**
 * Created by dprimenko on 16/12/16.
 */
public interface IMultiListProductMvp {
    interface View {
        void showUndoSnackbar(ArrayList<Product> products);
        ProductAdapter getAdapter();
    }

    interface Presenter {
        void setNewSelection(int position, boolean checked);
        void removeSelection(int position);
        void clearSelection();
        void deleteMultipleProducts();
        void addProduct(Product product);
        void restoreProducts(ArrayList<Product> products);
        void updateProduct();
    }
}
