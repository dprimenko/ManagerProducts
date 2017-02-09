package com.example.managerproducts.interfaces;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.widget.ArrayAdapter;

import com.example.managerproducts.adapter.CategoryAdapter;
import com.example.managerproducts.adapter.ProductAdapter;
import com.example.managerproducts.model.Category;
import com.example.managerproducts.model.Product;

import java.util.ArrayList;

/**
 * Created by usuario on 2/02/17.
 */

public interface IMultiListCategoryMvp {

    interface View {
        void showUndoSnackbar(ArrayList<Category> categories);
        CategoryAdapter getAdapter();
        Context getContext();
        Cursor getCursor();
        void setCursor(Cursor cursor);
    }

    interface Presenter {
        void setNewSelection(int position, boolean checked);
        void removeSelection(int position);
        void clearSelection();
        void deleteMultipleCategories();

        void restoreCategories(ArrayList<Category> categories);

        void addCategory(String category);
        void getAllCategories(CursorAdapter cursorAdapter);
        void updateCategory(Category category);
    }
}
