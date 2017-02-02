package com.example.managerproducts.presenter;

import android.os.AsyncTask;
import android.provider.ContactsContract;

import com.example.managerproducts.DatabaseManager;
import com.example.managerproducts.interfaces.IMultiListCategoryMvp;
import com.example.managerproducts.model.Category;
import com.example.managerproducts.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by usuario on 2/02/17.
 */

public class MultiListCategoryPresenter implements IMultiListCategoryMvp.Presenter {

    private IMultiListCategoryMvp.View view;
    private Map<Integer,Boolean> listSelectionItems;

    public static final int ADD_CATEGORY_REQUEST = 10;
    public static final int EDIT_CATEGORY_REQUEST = 11;

    public static final int DELETE_MULTIPLE_ITEMS = 31;

    public MultiListCategoryPresenter(IMultiListCategoryMvp.View view) {
        this.view = view;
        listSelectionItems = new HashMap<>();
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
    public void deleteMultipleCategories() {
        if (listSelectionItems.size() > 0) {

            ArrayList<Category> categories = new ArrayList<>();

            for (Map.Entry<Integer,Boolean> item:listSelectionItems.entrySet()) {
                Category category = (Category) view.getAdapter().getItem(item.getKey());
                categories.add(category);
                view.getAdapter().deleteCategory(category);
            }

            view.showUndoSnackbar(categories);
        }
    }

    @Override
    public void restoreCategories(ArrayList<Category> categories) {
        for (Category category:categories) {
            view.getAdapter().addCategory(category);
        }
    }

    @Override
    public void addCategory(String category) {
        DatabaseManager.getInstance().insertCategory(category);
    }

    @Override
    public void getAllCategories() {
        new GetAllCategories().execute();
    }

    @Override
    public void updateCategory(Category category) {
        view.getAdapter().updateCategory(category);
    }

    class GetAllCategories extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Object[] params) {
            view.getAdapter().addList(DatabaseManager.getInstance().getAllCategories());
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
        }
    }
}
