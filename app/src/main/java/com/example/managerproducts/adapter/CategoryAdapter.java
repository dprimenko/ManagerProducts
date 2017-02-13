package com.example.managerproducts.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.support.v4.widget.CursorAdapter;
import android.widget.TextView;

import com.example.managerproducts.R;
import com.example.managerproducts.db.DatabaseManager;
import com.example.managerproducts.model.Category;

import java.io.Serializable;
import java.util.List;

/**
 * Created by usuario on 2/02/17.
 */

public class CategoryAdapter extends CursorAdapter implements Serializable {

    public CategoryAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list_category, parent, false);

        CategoryHolder holder = new CategoryHolder();

        holder.txvCategoryName = (TextView) view.findViewById(R.id.txv_name_category);

        view.setTag(holder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        CategoryHolder holder = (CategoryHolder) view.getTag();
        holder.txvCategoryName.setText(cursor.getString(1));
    }

    @Override
    public Object getItem(int position) {

        getCursor().moveToPosition(position);

        Category category = new Category();

        category.setmId(getCursor().getInt(0));
        category.setmName(getCursor().getString(1));

        return super.getItem(position);
    }

    class CategoryHolder {
        TextView txvCategoryName;
    }
}
