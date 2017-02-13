package com.example.managerproducts.interfaces;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;

import com.example.managerproducts.adapter.CategoryAdapter;
import com.example.managerproducts.adapter.ProductAdapter;
import com.example.managerproducts.model.Category;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by usuario on 9/02/17.
 */

public interface IListPresenter {

    int CATEGORY = 1;
    int PRODUCT = 2;
    int DELETE_MULTIPLE_ITEMS = 31;

    interface View{
        void showUndoSnackbar(ArrayList<?> items);
        Context getContext();
        CursorAdapter getCursorAdapter();
        void setCursor(Cursor cursor);
    }

    interface Presenter {

        Context getContext();
        IListPresenter.View getView();
        Map<Integer, Boolean> getListSelectionItems();

        void getAllValues(CursorAdapter adapter);
        void restartLoader(CursorAdapter adapter);

        void setNewSelection(int position, boolean checked);
        void removeSelection(int position);
        void clearSelection();
        void deleteMultipleItems();
    }
}
