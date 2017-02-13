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

    interface View extends IListPresenter.View {
        int ADD_CATEGORY_REQUEST = 10;
        int EDIT_CATEGORY_REQUEST = 11;
    }
    interface Presenter {
        void restoreCategories(ArrayList<Category> categories);
        void addCategory(Category category);
    }
}
