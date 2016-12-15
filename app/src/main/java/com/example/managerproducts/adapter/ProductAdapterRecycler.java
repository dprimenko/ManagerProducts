package com.example.managerproducts.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.managerproducts.R;
import com.example.managerproducts.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by David Primenko on 21/10/16.
 */

public class ProductAdapterRecycler extends RecyclerView.Adapter<ProductAdapterRecycler.ProductViewHolder>{

    private List<Product> products;
    private Context context;
    private int sortRequestCode;
    private boolean asc = false;

    public ProductAdapterRecycler(Context context, int sortRequestCode){
        this.context = context;
        this.sortRequestCode = sortRequestCode;
        this.products = new ArrayList<Product>(((ProductApplication)context.getApplicationContext()).getListProducts(sortRequestCode));
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_product, parent, false);
        return new ProductViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.imgProduct.setImageResource(products.get(position).getmImage());
        holder.txvTitle.setText(products.get(position).getmName());
        holder.txvPrice.setText(String.valueOf(products.get(position).getmStock()));
        holder.txvStock.setText(String.valueOf(products.get(position).getmPrice()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduct;
        TextView txvTitle, txvPrice, txvStock;

        public ProductViewHolder(View itemView) {
            super(itemView);
            imgProduct = (ImageView) itemView.findViewById(R.id.imgProduct);
            txvTitle = (TextView) itemView.findViewById(R.id.txvTitle);
            txvPrice = (TextView) itemView.findViewById(R.id.txvPrice);
            txvStock = (TextView) itemView.findViewById(R.id.txvStock);
        }
    }

    public void sortList(int sortRequestCode) {
        products.clear();
        products.addAll(((ProductApplication)context.getApplicationContext()).getListProducts(sortRequestCode));
        if (asc) {
            Collections.reverse(products);
            asc = false;
        }
        else {
            asc = true;
        }

        notifyDataSetChanged(); // Notificacón a la vista. Patrón Observable-Observador
    }
}
