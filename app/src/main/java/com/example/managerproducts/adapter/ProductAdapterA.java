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

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by dprimenko on 21/10/16.
 */

public class ProductAdapterA extends ArrayAdapter<Product> {

    private Context context;

    public ProductAdapterA(Context context) {
        super(context, R.layout.item_list_product, ((ProductApplication)context.getApplicationContext()).getListProducts(0));

        this.context = context;
    }


    //region Forma Ineficiente!!
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imgProduct;
        TextView txvTitle;
        TextView txvPrice;
        TextView txvStock;

        //region 1. Crear un objeto inflater que inicializamos al LayoutInflater del contexto

        LayoutInflater inflater = LayoutInflater.from(context);
        //LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //endregion

        //region 2. Inflar la vista. Crear en memoria el objeto View que contiene los Widgets del XML

        View item = inflater.inflate(R.layout.item_list_product, null);     /* A parte del null se puede poner el ViewGroup,
                                                                               que es la lista, para que se cree una jerarquia
                                                                               vistas
                                                                            */
        //endregion

        //region 3. Asignar a las variables los Widget mediante el método findByViewId

        imgProduct = (ImageView) item.findViewById(R.id.imgProduct);
        txvTitle = (TextView) item.findViewById(R.id.txvTitle);
        txvPrice = (TextView) item.findViewById(R.id.txvPrice);
        txvStock = (TextView) item.findViewById(R.id.txvStock);

        //endregion

        //region 4. Asignar datos del adapter a los widget

        imgProduct.setImageResource(getItem(position).getmImage());
        txvTitle.setText(getItem(position).getmName());
        txvPrice.setText(getItem(position).getFormattedPrice());
        txvStock.setText(getItem(position).getFormattedStock());
        return item;

        //endregion
    }
    // endregion
}
