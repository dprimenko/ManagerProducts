package com.example.managerproducts.presenter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;

import com.example.managerproducts.adapter.CategoryAdapter;
import com.example.managerproducts.db.DatabaseContract;
import com.example.managerproducts.interfaces.ICategoryPresenter;
import com.example.managerproducts.interfaces.IListPresenter;
import com.example.managerproducts.interfaces.IListProductMvp;
import com.example.managerproducts.model.Category;
import com.example.managerproducts.model.Product;
import com.example.managerproducts.provider.ManageProductContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.managerproducts.interfaces.IListPresenter.CATEGORY;
import static com.example.managerproducts.interfaces.IListPresenter.PRODUCT;

/**
 * Created by usuario on 8/02/17.
 */

public class ListPresenterImpl implements LoaderManager.LoaderCallbacks<Cursor>, IListPresenter.Presenter {

    private IListPresenter.View view;
    private Context context;
    private Map<Integer,Boolean> listSelectionItems;
    private int id;


    public ListPresenterImpl(IListPresenter.View view, int id){

        this.view = view;
        this.context = view.getContext();
        this.id = id;
        this.listSelectionItems = new HashMap<>();
    }

    /*@Override
    public void onDestroy() {
        this.view = null;
    }*/

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        Loader loader = null;

        switch (id) {
            case CATEGORY:
                loader = new CursorLoader(
                        context,
                        ManageProductContract.Category.CONTENT_URI,
                        ManageProductContract.Category.PROJECTION,
                        null,
                        null,
                        DatabaseContract.CategoryEntry.DEFAULT_SORT);
                break;
            case PRODUCT:
                loader = new CursorLoader(
                        context,
                        ManageProductContract.Product.CONTENT_URI,
                        ManageProductContract.Product.PROJECTION,
                        null,
                        null,
                        null);
                break;
        }

        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        switch (loader.getId()) {
            case CATEGORY:
                view.getCursor().setNotificationUri(context.getContentResolver(), ManageProductContract.Category.CONTENT_URI);
                break;
            case PRODUCT:
                view.getCursor().setNotificationUri(context.getContentResolver(), ManageProductContract.Product.CONTENT_URI);
                break;
        }

        view.setCursor(cursor);

    }

    @Override
    public void onLoaderReset(Loader loader) {
        view.setCursor(null);
    }

    @Override
    public Context getContext() {
        return this.context;
    }

    @Override
    public IListPresenter.View getView() {
        return this.view;
    }

    @Override
    public Map<Integer, Boolean> getListSelectionItems() {
        return listSelectionItems;
    }

    @Override
    public void setNewSelection(int position, boolean checked) {
        listSelectionItems.remove(position);
        listSelectionItems.put(position, checked);
    }

    @Override
    public void removeSelection(int position) {
        listSelectionItems.remove(position);
    }

    @Override
    public void clearSelection() {
        listSelectionItems.clear();
    }

    @Override
    public void deleteMultipleItems() {
        if (listSelectionItems.size() > 0) {

            switch (id) {
                case CATEGORY:
                    ArrayList<Category> categories = new ArrayList<>();
                    for (Map.Entry<Integer,Boolean> item:listSelectionItems.entrySet()) {
                        Category category = (Category) view.getCategoryAdapter().getItem(item.getKey());
                        categories.add(category);
                        view.getCategoryAdapter().deleteCategory(category);
                    }
                    view.showUndoSnackbar(categories);
                    break;
                case PRODUCT:
                    ArrayList<Product> products = new ArrayList<>();
                    for (Map.Entry<Integer,Boolean> item:listSelectionItems.entrySet()) {
                        Product product = (Product) view.getProductAdapter().getItem(item.getKey());
                        products.add(product);
                        view.getProductAdapter().deleteProduct(product);
                    }
                    view.showUndoSnackbar(products);
                    break;
            }

        }
    }

    @Override
    public void getAllValues(CursorAdapter cursorAdapter) {
        ((AppCompatActivity)context).getSupportLoaderManager().initLoader(id, null, this);
    }

    @Override
    public void restartLoader(CursorAdapter adapter) {
        ((AppCompatActivity)context).getSupportLoaderManager().restartLoader(id, null, this);
    }


}
