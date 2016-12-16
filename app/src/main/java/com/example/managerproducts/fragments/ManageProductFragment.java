package com.example.managerproducts.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.managerproducts.R;
import com.example.managerproducts.interfaces.IManageProductMvp;
import com.example.managerproducts.model.Product;
import com.example.managerproducts.presenter.ManageProductPresenter;
import com.example.managerproducts.presenter.MultiListProductPresenter;

/**
 * Created by dprimenko on 15/12/16.
 */
public class ManageProductFragment extends Fragment implements IManageProductMvp.View {

    private static final int  MAX_VALUE_PRICE = 8;
    private static final int  MAX_VALUE_CONCENTRATION = 5;
    private RelativeLayout rlManageProduct;
    private EditText edtName;
    private EditText edtDescription;
    private EditText edtBrand;
    private EditText edtPrice;
    private EditText edtConcentration;
    private IManageProductMvp.Presenter presenter;
    private ManageProductFragmentListener mCallback;

    public ManageProductFragment() {
    }

    public static ManageProductFragment newInstance(Bundle bundle) {
        ManageProductFragment manageProductFragment = new ManageProductFragment();
        manageProductFragment.setArguments(bundle);
        return manageProductFragment;
    }

    public interface ManageProductFragmentListener {
        void onMultiListProductFragment(Bundle bundle);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (ManageProductFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement ManageProductFragmentListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ManageProductPresenter(this, getContext());
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_manage_product, container, false);

        if (rootView != null) {
            rlManageProduct = (RelativeLayout) rootView.findViewById(R.id.rl_manage_product);
            edtName = (EditText) rootView.findViewById(R.id.edt_name);
            edtDescription = (EditText) rootView.findViewById(R.id.edt_description);
            edtBrand = (EditText) rootView.findViewById(R.id.edt_brand);
            edtPrice = (EditText) rootView.findViewById(R.id.edt_price);
            edtConcentration = (EditText) rootView.findViewById(R.id.edt_concentration);

            edtPrice.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MAX_VALUE_PRICE)});
            edtConcentration.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MAX_VALUE_CONCENTRATION)});
        }

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_manage_product, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_add_product) {
            presenter.validateFields(
                    edtName.getText().toString(),
                    edtDescription.getText().toString(),
                    edtBrand.getText().toString(),
                    edtPrice.getText().toString(),
                    edtConcentration.getText().toString(),
                    MultiListProductPresenter.ADD_PRODUCT_REQUEST);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public void setMessageError(int messageError) {
        Snackbar.make(rlManageProduct, getActivity().getString(messageError), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void backToListProductFragment(Product product, int request) {

        Bundle bundle = new Bundle();
        bundle.putParcelable("product_key", product);
        bundle.putInt("manage_request", request);
        mCallback.onMultiListProductFragment(bundle);
    }
}
