package com.example.managerproducts.presenter;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.example.managerproducts.db.DatabaseContract;
import com.example.managerproducts.interfaces.ICategoryPresenter;
import com.example.managerproducts.interfaces.IListPresenter;
import com.example.managerproducts.interfaces.IListProductMvp;
import com.example.managerproducts.provider.ManageProductContract;

/**
 * Created by usuario on 8/02/17.
 */

public class ListPresenterImpl implements LoaderManager.LoaderCallbacks<Cursor>, IListPresenter {

    private IListPresenter.View view;
    private final static int CATEGORY = 1;
    private final static int PRODUCT = 2;
    private Context context;


    public ListPresenterImpl(IListPresenter.View view){

        this.view = view;
        this.context = view.getContext();
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
                break;
        }

        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        view.setCursor(cursor);
        view.getCursor().setNotificationUri(context.getContentResolver(), ManageProductContract.Category.CONTENT_URI);
    }

    @Override
    public void onLoaderReset(Loader loader) {
        view.setCursor(null);
    }

    @Override
    public void getAllItems(int resource) {

    }
}
