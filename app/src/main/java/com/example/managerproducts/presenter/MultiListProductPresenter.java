package com.example.managerproducts.presenter;

import android.text.BoringLayout;
import android.util.Log;

import com.example.managerproducts.DatabaseManager;
import com.example.managerproducts.interfaces.IListProductMvp;
import com.example.managerproducts.interfaces.IMultiListProductMvp;
import com.example.managerproducts.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Created by usuario on 16/12/16.
 */

public class MultiListProductPresenter implements IMultiListProductMvp.Presenter {

    private IMultiListProductMvp.View view;
    private Map<Integer,Boolean> listSelectionProducts;

    public static final int ADD_PRODUCT_REQUEST = 10;
    public static final int EDIT_PRODUCT_REQUEST = 11;

    public static final int DELETE_MULTIPLE_ITEMS = 31;

    public MultiListProductPresenter(IMultiListProductMvp.View view) {
        this.view = view;
        listSelectionProducts = new HashMap<>();
    }

    @Override
    public void setNewSelection(int position, boolean checked) {
        listSelectionProducts.remove(position);
        listSelectionProducts.put(position, checked);
    }

    @Override
    public void removeSelection(int position) {
        listSelectionProducts.remove(position);
    }

    @Override
    public void clearSelection() {
        listSelectionProducts.clear();
    }

    @Override
    public void addProduct(Product product) {
        view.getAdapter().addProduct(product);
    }

    @Override
    public void restoreProducts(ArrayList<Product> products) {
        for (Product product:products) {
            view.getAdapter().addProduct(product);
        }
    }

    @Override
    public void getAllProducts() {

        view.getAdapter().addList();
    }

    @Override
    public void updateProduct(Product product) {
        view.getAdapter().updateProduct(product);
    }

    @Override
    public void deleteMultipleProducts() {

        if (listSelectionProducts.size() > 0) {

            ArrayList<Product> products = new ArrayList<>();

            for (Map.Entry<Integer,Boolean> item:listSelectionProducts.entrySet()) {
                Product product = view.getAdapter().getItem(item.getKey());
                products.add(product);
                view.getAdapter().deleteProduct(product);
            }

            view.showUndoSnackbar(products);
        }
    }
}
