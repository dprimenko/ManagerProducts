package com.example.managerproducts.presenter;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;

import com.example.managerproducts.db.DatabaseContract;
import com.example.managerproducts.db.DatabaseManager;
import com.example.managerproducts.interfaces.IListPresenter;
import com.example.managerproducts.interfaces.IMultiListCategoryMvp;
import com.example.managerproducts.model.Category;
import com.example.managerproducts.provider.ManageProductContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by usuario on 2/02/17.
 */

public class MultiListCategoryPresenter extends ListPresenterImpl implements IMultiListCategoryMvp.Presenter, LoaderManager.LoaderCallbacks<Cursor> {

    public MultiListCategoryPresenter(IListPresenter.View view) {
        super(view, IListPresenter.CATEGORY);
    }

    @Override
    public void restoreCategories(ArrayList<Category> categories) {
        for (Category category:categories) {
            addCategory(category);
        }
    }

    @Override
    public void addCategory(Category category) {
        ContentValues contentValues = getContentValuesCategory(category);
        getContext().getContentResolver().insert(ManageProductContract.Category.CONTENT_URI, contentValues);
    }

    private ContentValues getContentValuesCategory(Category category) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ManageProductContract.Product.NAME, category.getmName());
        return contentValues;
    }
}
