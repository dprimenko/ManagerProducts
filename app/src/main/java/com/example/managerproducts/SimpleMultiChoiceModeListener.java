package com.example.managerproducts;

import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;

import com.example.managerproducts.presenter.ListProductPresenter;
import com.example.managerproducts.presenter.ManageProductPresenter;
import com.example.managerproducts.presenter.MultiListCategoryPresenter;
import com.example.managerproducts.presenter.MultiListProductPresenter;

import java.util.AbstractList;

/**
 * Created by usuario on 16/12/16.
 */

public class SimpleMultiChoiceModeListener implements AbsListView.MultiChoiceModeListener{


    private StateContextMenuListener mCallback;
    private int cont;

    public SimpleMultiChoiceModeListener(Fragment fragment) {;
        this.cont = 0;

        try {
            mCallback = (StateContextMenuListener) fragment;
        } catch (ClassCastException e) {
            throw new ClassCastException(fragment.toString() + " must implement StateContextMenu");
        }
    }

    public interface StateContextMenuListener {
        void onCreatedContextMenu();
        void onItemCheckedStateChanged(int position, boolean checked);
        void onPrepareActionModeContextMenu();
        void onActionItemClicked(int action);
        void onDestroyedContextMenu();
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
        if (checked) {
            cont++;
        } else {
            cont--;
        }
        mCallback.onItemCheckedStateChanged(position, checked);
        mode.setTitle(String.valueOf(cont));
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.menu_action_mode_list, menu);
        mCallback.onCreatedContextMenu();
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        mCallback.onPrepareActionModeContextMenu();
        return true;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_del_multiple:
                mCallback.onActionItemClicked(MultiListCategoryPresenter.DELETE_MULTIPLE_ITEMS);
                break;
        }
        mode.finish();
        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        cont = 0;
        mCallback.onDestroyedContextMenu();
    }
}
