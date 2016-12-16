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
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.managerproducts.R;
import com.example.managerproducts.SimpleMultiChoiceModeListener;
import com.example.managerproducts.adapter.ProductAdapter;
import com.example.managerproducts.interfaces.IListProductMvp;
import com.example.managerproducts.presenter.ListProductPresenter;

/**
 * Created by dprimenko on 15/12/16.
 */

public class MultiListProductsFragment extends Fragment implements SimpleMultiChoiceModeListener.StateContextMenuListener, IListProductMvp.View{

    private FloatingActionButton btnAddProduct;
    private ListView lwProducts;
    private ProductAdapter adapter;
    private ListProductPresenter presenter;

    private static final int ADD_PRODUCT_REQUEST = 0;
    private static final int EDIT_PRODUCT_REQUEST = 1;

    private ListProductFragmentListener mCallback;

    @Override
    public void onCreatedContextMenu() {
        btnAddProduct.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyedContextMenu() {
        btnAddProduct.setVisibility(View.VISIBLE);
    }

    public interface ListProductFragmentListener {
        void onManageProductFragmentListener(Bundle bundle);
    }

    public static MultiListProductsFragment newInstance(Bundle bundle) {
        MultiListProductsFragment manageProductFragment = new MultiListProductsFragment();
        manageProductFragment.setArguments(bundle);
        return manageProductFragment;
    }

    public MultiListProductsFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (ListProductFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement ListProductFragmentListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        presenter = new ListProductPresenter(this);
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lwProducts.setAdapter(adapter);
        lwProducts.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        SimpleMultiChoiceModeListener mcl = new SimpleMultiChoiceModeListener(presenter, getActivity());
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
    public void showUndoSnackbar() {

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
