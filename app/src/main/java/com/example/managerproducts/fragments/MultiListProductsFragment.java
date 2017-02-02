package com.example.managerproducts.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.managerproducts.R;
import com.example.managerproducts.SimpleMultiChoiceModeListener;
import com.example.managerproducts.adapter.ProductAdapter;
import com.example.managerproducts.interfaces.IMultiListProductMvp;
import com.example.managerproducts.model.Product;
import com.example.managerproducts.presenter.MultiListProductPresenter;

import java.util.ArrayList;

/**
 * Created by dprimenko on 15/12/16.
 */

public class MultiListProductsFragment extends Fragment implements SimpleMultiChoiceModeListener.StateContextMenuListener, IMultiListProductMvp.View{

    private FloatingActionButton btnAddProduct;
    private ListView lwProducts;
    private ProductAdapter adapter;
    private MultiListProductPresenter presenter;

    private int statusBarColor;

    private MultiListProductFragmentListener mCallback;

    @Override
    public ProductAdapter getAdapter() {
        return adapter;
    }


    public interface MultiListProductFragmentListener {
        void onManageProductFragmentListener(Bundle bundle);
    }

    public static MultiListProductsFragment newInstance(Bundle bundle) {
        MultiListProductsFragment manageProductFragment = new MultiListProductsFragment();
        manageProductFragment.setArguments(bundle);
        return manageProductFragment;
    }

    public MultiListProductsFragment() {
        presenter = new MultiListProductPresenter(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        adapter = new ProductAdapter(getActivity(), R.layout.item_list_product);
        getRequestManageProduct();
        presenter.getAllProducts();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (MultiListProductFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement MultiListProductFragmentListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_list_products, container, false);

        if (rootView != null) {
            lwProducts = (ListView) rootView.findViewById(R.id.lw_products);
            btnAddProduct = (FloatingActionButton) rootView.findViewById(R.id.btn_add_product);

            lwProducts.setAdapter(adapter);

            btnAddProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallback.onManageProductFragmentListener(null);
                }
            });
        }

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lwProducts.setAdapter(adapter);
        lwProducts.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        SimpleMultiChoiceModeListener mcl = new SimpleMultiChoiceModeListener(this);
        lwProducts.setMultiChoiceModeListener(mcl);
        lwProducts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                lwProducts.setItemChecked(position, !(lwProducts.getCheckedItemPositions().get(position)));
                return false;
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_product, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_sort_alphabetically:
                break;
            case R.id.action_settings_general:
                break;
            case R.id.action_settings_account:
                break;
            case R.id.action_logout:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter = null;
    }


    // Métodos de la interfaz IManageProductMvp.View
    @Override
    public void showUndoSnackbar(final ArrayList<Product> products) {
        Snackbar.make(getView(), getString(R.string.items_deleted), Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.restoreProducts(products);
                        scrollDown();
                    }
                }).show();
    }

    // Métodos del Callback del menu contextual
    @Override
    public void onCreatedContextMenu() {
        btnAddProduct.setVisibility(View.GONE);
    }

    @Override
    public void onItemCheckedStateChanged(int position, boolean checked) {
        if (checked) {
            presenter.setNewSelection(position, checked);
        } else  {
            presenter.removeSelection(position);
        }
    }

    @Override
    public void onPrepareActionModeContextMenu() {

        //Estoy investigando por que no se cambiar el color de la barra de estado
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            statusBarColor = getActivity().getWindow().getStatusBarColor();

             getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));
        }*/
    }

    @Override
    public void onActionItemClicked(int action) {
        switch (action) {
            case MultiListProductPresenter.DELETE_MULTIPLE_ITEMS:
                presenter.deleteMultipleProducts();
                break;
        }
    }

    @Override
    public void onDestroyedContextMenu() {

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(statusBarColor);
        }*/
        btnAddProduct.setVisibility(View.VISIBLE);
        presenter.clearSelection();
    }

    // Métodos propios del fragment

    private void getRequestManageProduct() {

        if (getArguments() != null) {
            Product product = ((Product)getArguments().getParcelable("product_key"));

            if (getArguments().getInt("manage_request") == MultiListProductPresenter.ADD_PRODUCT_REQUEST) {
                presenter.addProduct(product);
            }
            else if (getArguments().getInt("manage_request") == MultiListProductPresenter.EDIT_PRODUCT_REQUEST) {
            }
        }
    }

    private void scrollDown() {
        lwProducts.smoothScrollToPosition((adapter.getCount() - 1));
    }
}
