package com.example.managerproducts.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.managerproducts.ProductApplication;
import com.example.managerproducts.R;
import com.example.managerproducts.model.Product;

/**
 * Created by dprimenko on 21/10/16.
 */

public class ProductAdapter extends ArrayAdapter<Product> {

    private Context context;

    public ProductAdapter(Context context) {
        super(context, R.layout.item_list_product, ((ProductApplication)context.getApplicationContext()).getListProducts(0));

        this.context = context;
    }


    //region Lo más eficiente!!
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View item = convertView;
        ProductHolder productHolder;
        boolean par = ((position % 2) == 0);

        if (item == null) {
            // 1. Crear un objeto inflater que inicializamos al LayoutInflater del contexto
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //2. Inflar la vista. Crear en memoria el objeto View que contiene los Widgets del XML
            item = inflater.inflate(R.layout.item_list_product, null);

            productHolder = new ProductHolder();

        // 3. Asignar a las variables los Widget mediante el método findByViewId

            productHolder.imgProduct = (ImageView) item.findViewById(R.id.imgProduct);
            productHolder.txvTitle = (TextView) item.findViewById(R.id.txvTitle);
            productHolder.txvPrice = (TextView) item.findViewById(R.id.txvPrice);
            productHolder.txvStock = (TextView) item.findViewById(R.id.txvStock);

            item.setTag(productHolder);

        }

        else {
            productHolder = (ProductHolder) item.getTag();
        }

        //region 4. Asignar datos del adapter a los widget

        productHolder.imgProduct.setImageResource(getItem(position).getmImage());
        productHolder.txvTitle.setText(getItem(position).getmName());
        productHolder.txvPrice.setText(getItem(position).getFormattedPrice());
        productHolder.txvStock.setText(getItem(position).getFormattedStock());

        if (par) {
            item.setBackgroundResource(R.color.altColor);
        }

        else {
            item.setBackgroundResource(R.color.btnText);
        }

        return item;

        //endregion
    }
    // endregion

    /* Clase Interna que contiene los Widget
    *  del fichero XML
    */

    class ProductHolder {
        ImageView imgProduct;
        TextView txvTitle;
        TextView txvPrice;
        TextView txvStock;
    }
}
