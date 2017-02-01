package com.example.managerproducts.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.managerproducts.R;
import com.example.managerproducts.adapter.ProductAdapter;

/**
 * Created by dprimenko on 15/12/16.
 */

public class ListProductsFragment extends Fragment {

    private FloatingActionButton btnAddProduct;
    private ListView lwProducts;
    private ProductAdapter adapter;

    private static final int ADD_PRODUCT_REQUEST = 0;
    private static final int EDIT_PRODUCT_REQUEST = 1;

    private ListProductFragmentListener mCallback;

    public interface ListProductFragmentListener {
        void onManageProductFragmentListener(Bundle bundle);
    }

    public static ListProductsFragment newInstance(Bundle bundle) {
        ListProductsFragment manageProductFragment = new ListProductsFragment();
        manageProductFragment.setArguments(bundle);
        return manageProductFragment;
    }

    public ListProductsFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (ListProductFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement MultiListProductFragmentListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_list_products, container, false);

        if (rootView != null) {
            adapter = new ProductAdapter(getActivity(), R.layout.item_list_product);
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
}
