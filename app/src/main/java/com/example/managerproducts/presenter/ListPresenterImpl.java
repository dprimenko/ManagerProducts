package com.example.managerproducts.presenter;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.example.managerproducts.interfaces.ICategoryPresenter;
import com.example.managerproducts.provider.ManageProductContract;

/**
 * Created by usuario on 8/02/17.
 */

public class ListPresenterImpl implements LoaderManager.LoaderCallbacks<Cursor> {

    private ICategoryPresenter.View view;
    private final static int CATEGORY = 1;
    private Context context;


    public ListPresenterImpl(ICategoryPresenter.View view){

        this.view = view;
        this.context = view.getContext();
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

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
                        ManageProductContract.Category.DEFAULT_SORT);
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
}
