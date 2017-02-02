package com.example.managerproducts.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.managerproducts.R;
import com.example.managerproducts.SimpleMultiChoiceModeListener;
import com.example.managerproducts.adapter.CategoryAdapter;
import com.example.managerproducts.adapter.ProductAdapter;
import com.example.managerproducts.interfaces.IMultiListCategoryMvp;
import com.example.managerproducts.model.Category;
import com.example.managerproducts.model.Product;
import com.example.managerproducts.presenter.MultiListCategoryPresenter;
import com.example.managerproducts.presenter.MultiListProductPresenter;

import java.util.ArrayList;

/**
 * Created by usuario on 2/02/17.
 */

public class MultiListCategoriesFragment extends Fragment implements IMultiListCategoryMvp.View {

    private FloatingActionButton btnAddCategory;
    private ListView lwCategories;
    private CategoryAdapter adapter;
    private MultiListCategoriesFragmentListener mCallback;
    private MultiListCategoryPresenter presenter;

    @Override
    public CategoryAdapter getAdapter() {
        return adapter;
    }

    public interface MultiListCategoriesFragmentListener {
        void onManageCategoryFragmentListener(Bundle bundle);
    }

    public static MultiListCategoriesFragment newInstance(Bundle bundle) {
        MultiListCategoriesFragment manageCategoryFragment = new MultiListCategoriesFragment();
        manageCategoryFragment.setArguments(bundle);
        return manageCategoryFragment;
    }

    public MultiListCategoriesFragment() {
        presenter = new MultiListCategoryPresenter(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        adapter = new CategoryAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item);
        getRequestManageProduct();
        presenter.getAllCategories();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (MultiListCategoriesFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement MultiListCategoriesFragmentListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_list_categories, container, false);

        if (rootView != null) {
            lwCategories = (ListView) rootView.findViewById(R.id.lw_categories);
            btnAddCategory = (FloatingActionButton) rootView.findViewById(R.id.btn_add_category);

            lwCategories.setAdapter(adapter);

            btnAddCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallback.onManageCategoryFragmentListener(null);
                }
            });
        }

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lwCategories.setAdapter(adapter);
        lwCategories.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        SimpleMultiChoiceModeListener mcl = new SimpleMultiChoiceModeListener(this);
        lwCategories.setMultiChoiceModeListener(mcl);
        lwCategories.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                lwCategories.setItemChecked(position, !(lwCategories.getCheckedItemPositions().get(position)));
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
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter = null;
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
    public void showUndoSnackbar(final ArrayList<Category> categories) {
        Snackbar.make(getView(), getString(R.string.items_deleted), Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.restoreCategories(categories);
                        scrollDown();
                    }
                }).show();
    }

    private void getRequestManageProduct() {

        if (getArguments() != null) {
            Category category = ((Category)getArguments().getParcelable("category_key"));

            if (getArguments().getInt("manage_request") == MultiListProductPresenter.ADD_PRODUCT_REQUEST) {
                presenter.addCategory(category);
            }
            else if (getArguments().getInt("manage_request") == MultiListProductPresenter.EDIT_PRODUCT_REQUEST) {
            }
        }
    }

    private void scrollDown() {
        lwCategories.smoothScrollToPosition((adapter.getCount() - 1));
    }
}
