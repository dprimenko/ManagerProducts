package com.example.managerproducts;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;

import com.example.managerproducts.presenter.ListProductPresenter;

import java.util.AbstractList;

/**
 * Created by usuario on 16/12/16.
 */

public class SimpleMultiChoiceModeListener implements AbsListView.MultiChoiceModeListener{


    private ListProductPresenter presenter;
    private StateContextMenuListener mCallback;
    private Context context;
    private int statusBarColor;
    private int cont;

    public SimpleMultiChoiceModeListener(ListProductPresenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
        this.cont = 0;

        try {
            mCallback = (StateContextMenuListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement StateContextMenu");
        }
    }

    public interface StateContextMenuListener {
        void onCreatedContextMenu();
        void onDestroyedContextMenu();
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
        if (checked) {
            cont++;
            //presenter.setNewSelection(position, checked);
        } else {
            cont--;
            //presenter.removeSelection(position);
        }
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            statusBarColor = ((AppCompatActivity)context).getWindow().getStatusBarColor();
            ((AppCompatActivity)context).getWindow().setStatusBarColor(ContextCompat.getColor(context, R.color.colorAccent));
        }
        return true;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_del_multiple:
                presenter.deleteMultipleProducts();
                break;
        }
        mode.finish();
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        mCallback.onDestroyedContextMenu();

        //presenter.clearSelection();
        mode.finish();
    }
}
