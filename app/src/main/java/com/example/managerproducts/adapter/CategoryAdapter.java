package com.example.managerproducts.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.managerproducts.DatabaseManager;
import com.example.managerproducts.model.Category;
import com.example.managerproducts.model.Product;

import java.util.List;

/**
 * Created by usuario on 2/02/17.
 */

public class CategoryAdapter extends ArrayAdapter {
    public CategoryAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void addList(List<Category> categories) {
        clear();
        addAll(categories);
    }

    public void addCategory(Category category) {
        DatabaseManager.getInstance().insertCategory(category.getmName());
        notifyDataSetChanged();
    }

    public void updateCategory(Category category) {
        //DatabaseManager.getInstance().updateCategory(category);
        notifyDataSetChanged();
    }

    public void deleteCategory(Category category) {
        //DatabaseManager.getInstance().deleteCategory(category);
        notifyDataSetChanged();
    }
}
