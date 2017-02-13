package com.example.managerproducts.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.managerproducts.R;
import com.example.managerproducts.db.DatabaseContract;
import com.example.managerproducts.interfaces.IListPresenter;
import com.example.managerproducts.model.Product;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import static com.example.managerproducts.ManageProductApplication.getContext;

/**
 * Created by dprimenko on 21/10/16.
 */

public class ProductAdapter extends CursorAdapter implements Serializable {

    public ProductAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_list_product, parent, false);
        ProductHolder holder = new ProductHolder();

        holder.imgProduct = (ImageView) view.findViewById(R.id.imgProduct);
        holder.txvTitle = (TextView) view.findViewById(R.id.txvTitle);
        holder.txvStock = (TextView) view.findViewById(R.id.txvStock);
        holder.txvPrice = (TextView) view.findViewById(R.id.txvPrice);

        view.setTag(holder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ProductHolder holder = (ProductHolder) view.getTag();

        Picasso.with(context).load(cursor.getString(cursor.getColumnIndex(DatabaseContract.ProductEntry.COLUMN_IMAGE)));
        holder.txvTitle.setText(cursor.getString(cursor.getColumnIndex(DatabaseContract.ProductEntry.COLUMN_NAME)));
        holder.txvStock.setText(String.valueOf(cursor.getInt(cursor.getColumnIndex(DatabaseContract.ProductEntry.COLUMN_STOCK))));
        holder.txvPrice.setText(String.valueOf(cursor.getDouble(cursor.getColumnIndex(DatabaseContract.ProductEntry.COLUMN_PRICE))));
    }

    @Override
    public Object getItem(int position) {

        getCursor().moveToPosition(position);

        Product product = new Product();
        product.setmId(getCursor().getInt(getCursor().getColumnIndex(DatabaseContract.ProductEntry._ID)));
        product.setmName(getCursor().getString(getCursor().getColumnIndex(DatabaseContract.ProductEntry.COLUMN_NAME)));
        product.setmDescription(getCursor().getString(getCursor().getColumnIndex(DatabaseContract.ProductEntry.COLUMN_DESCRIPTION)));
        product.setmBrand(getCursor().getString(getCursor().getColumnIndex(DatabaseContract.ProductEntry.COLUMN_BRAND)));
        product.setmConcentration(getCursor().getString(getCursor().getColumnIndex(DatabaseContract.ProductEntry.COLUMN_DOSAGE)));
        product.setmPrice(getCursor().getDouble(getCursor().getColumnIndex(DatabaseContract.ProductEntry.COLUMN_PRICE)));
        product.setmStock(getCursor().getInt(getCursor().getColumnIndex(DatabaseContract.ProductEntry.COLUMN_STOCK)));
        product.setmImage(getCursor().getString(getCursor().getColumnIndex(DatabaseContract.ProductEntry.COLUMN_IMAGE)));

        return product;
    }

    class ProductHolder {
        ImageView imgProduct;
        TextView txvTitle;
        TextView txvPrice;
        TextView txvStock;
    }
}
